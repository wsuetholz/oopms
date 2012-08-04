/**
 * Licensed to Jasig under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Jasig licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.jasig.portal.security.provider;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasig.portal.AuthorizationException;
import org.jasig.portal.groups.GroupsException;
import org.jasig.portal.groups.IEntityGroup;
import org.jasig.portal.groups.IGroupMember;
import org.jasig.portal.portlet.om.IPortletDefinition;
import org.jasig.portal.security.IAuthorizationPrincipal;
import org.jasig.portal.security.IAuthorizationService;
import org.jasig.portal.security.IPermission;
import org.jasig.portal.security.IPermissionPolicy;
import org.jasig.portal.services.GroupService;
import org.springframework.stereotype.Service;

/**
 * If there exists a GRANT explicitly for the Principal for the Activity under consideration,
 * this permission policy will GRANT permission.
 *
 *  If there exists a GRANT for a group containing the Principal for the Activity
 *  under consideration, and there is a path up the groups tree from the Principal
 *  to that GRANTed group that is not interrupted by a Deny for an intervening
 *  group in the tree, then this permission policy will GRANT permission.
 *
 *  Otherwise, this permission policy will DENY permission.
 *
 *  Examples:
 *  Principal (GRANT) -- Small group -- Bigger group -- Huge group
 *  Results in GRANT because the Principal has an explicit permission.
 *
 *  Principal -- Small group -- Bigger group (GRANT) -- Huge group
 *  Results in GRANT because there is an unblocked path to a containing group
 *  with GRANT.
 *
 *  Principal -- Small group (DENY) -- Bigger group (GRANT) -- Huge group
 *  Results in DENY because there is no unblocked path to a grant --
 *  the "Bigger group"'s GRANT does not apply because of the intervening DENY.
 *
 *  Principal -- Small group (DENY) -- Bigger group -- Huge group
 *  Principal -- Some other group -- Bigger other group (GRANT) -- Huge group
 *  Results in GRANT because there is an unblocked path to a GRANT.
 */
@Service("anyUnblockedGrantPermissionPolicy")
public class AnyUnblockedGrantPermissionPolicy
    implements IPermissionPolicy {

    protected final Log log = LogFactory.getLog(getClass());
    

    public boolean doesPrincipalHavePermission(IAuthorizationService service, IAuthorizationPrincipal principal, String owner, String activity, String target) throws AuthorizationException {
        // the API states that the service, owner, and activity arguments must 
        // not be null. If for some reason they are null, log and fail closed
        // The principal must also not be null.
        if (service == null || principal == null || owner == null || activity == null) {

            log.error("Null argument to AnyUnblockedGrantPermissionPolicy doesPrincipalHavePermission() method " +
                    "should not be possible.  This is indicative of a potentially serious bug in the " +
                    "permissions and authorization infrastructure. " +
                    "service= [" + service + "] principal = [" + principal + "] owner = [" + owner + "] activity = [" + activity + "] target = [" + target + "]");

            // fail closed
            return false;
        }

        // if this user is a super-user, just return true
        if (!IPermission.ALL_PERMISSIONS_ACTIVITY.equals(activity)
                && doesPrincipalHavePermission(service, principal,
                        IPermission.PORTAL_SYSTEM,
                        IPermission.ALL_PERMISSIONS_ACTIVITY,
                        IPermission.ALL_TARGET)) {
            return true;
        }
        
        // first check for explicit permissions for this Principal
        IPermission[] perms = service.getPermissionsForPrincipal(principal, owner, activity, target);

        Set<IPermission> activePermissions = activePermissions(perms);

        if (containsType(activePermissions, IPermission.PERMISSION_TYPE_DENY)) {
            if (log.isTraceEnabled()) {
            	log.trace("Principal [" + principal + "] is explicitly denied permission to perform activity [" + activity + "] on target [" + target + "] under permission owning system [" + owner + "].");
            }
            return false;
        }

        if (containsType(activePermissions, IPermission.PERMISSION_TYPE_GRANT)) {
            // explicit GRANT
            if (log.isTraceEnabled()) {
            	log.trace("Principal [" + principal + "] is granted permission to perform activity [" + activity + "] on target [" + target + "] under permission owning system [" + owner + "] because this principal has an excplicit GRANT and does not have an exlicit DENY.");
            }
            return true;
        }

        // if the target is formatted as a channel, check if the user has
        // the ALL_CHANNELS permission
        if (target.startsWith(IPermission.PORTLET_PREFIX)
                    && doesPrincipalHavePermission(service, principal, owner,
                            activity, IPermission.ALL_PORTLETS_TARGET)) {
            return true;
        }

        // if this target corresponds to a group or category, check if the user
        // has the ALL_CATEGORIES or ALL_GROUPS permissions
        IEntityGroup targetGroup = GroupService.findGroup(target);
        if (targetGroup != null) {
            if ((targetGroup.getEntityType().equals(IPortletDefinition.class)
                    && doesPrincipalHavePermission(service, principal, owner,
                            activity, IPermission.ALL_CATEGORIES_TARGET) || doesPrincipalHavePermission(
                    service, principal, owner, activity,
                    IPermission.ALL_GROUPS_TARGET))) {
                return true;
            }
        }

        // no explicit permission.  Search for an unblocked GRANT.
        boolean hasUnblockedPathToGrant;
        try {
            // track groups we've already explored to avoid infinite loop
            Set<IGroupMember> seenGroups = new HashSet<IGroupMember>(100);
            hasUnblockedPathToGrant = hasUnblockedPathToGrant(service, principal, owner, activity, target, seenGroups);
        } catch (Exception e) {
            log.error("Error searching for unblocked path to grant for principal [" + principal + "]", e);
            // fail closed
            return false;
        }
        
        if (log.isTraceEnabled()) {
        	if (hasUnblockedPathToGrant) {
        		log.trace("Principal [" + principal + "] is granted permission to perform activity [" + activity + "] on target [" + target + "] under permission owning system [" + owner + "] because this principal has an unblocked path to a GRANT.");
        	} else {
        		log.trace("Principal [" + principal + "] is denied permission to perform activity [" + activity + "] on target [" + target + "] under permission owning system [" + owner + "] because this principal does not have an unblocked path to a GRANT.");
        	}
        }
        
        return hasUnblockedPathToGrant;

    }

    private boolean hasUnblockedPathToGrant(IAuthorizationService service, IAuthorizationPrincipal principal, String owner, String activity, String target, Set<IGroupMember> seenGroups) throws GroupsException {

    	if (log.isTraceEnabled()) {
    		log.trace("Searching for unblocked path to GRANT for principal [" + principal + "] to [" + activity + "] on target [" + target + "] having already checked ["+ seenGroups + "]");
    	}
    	
        IGroupMember principalAsGroupMember = service.getGroupMember(principal);

        if (seenGroups.contains(principalAsGroupMember)) {
        	
        	if (log.isTraceEnabled()) {
        		log.trace("Declining to re-examine principal [" + principal + "] for permission to [" + activity + "] on [" + target + "] because this group is among already checked groups [" + seenGroups + "]");
        	}
        	
            return false;
        }

        seenGroups.add(principalAsGroupMember);



        Iterator<IGroupMember> immediatelyContainingGroups = principalAsGroupMember.getContainingGroups();

        while (immediatelyContainingGroups.hasNext()) {
            IGroupMember parentGroup = immediatelyContainingGroups.next();
            try {
                if (parentGroup != null) {
                    IAuthorizationPrincipal parentPrincipal = service.newPrincipal( parentGroup );
                    IPermission[] parentPermissions = service.getPermissionsForPrincipal(parentPrincipal, owner, activity, target);

                    Set<IPermission> activeParentPermissions = activePermissions(parentPermissions);

                    boolean parentPermissionsContainsDeny = containsType(activeParentPermissions, IPermission.PERMISSION_TYPE_DENY);
                    boolean parentPermissionsContainsGrant = containsType(activeParentPermissions, IPermission.PERMISSION_TYPE_GRANT);

                    if (parentPermissionsContainsGrant && ! parentPermissionsContainsDeny) {
                        // there's a GRANT on this group to which we had an unblocked path, and
                        // there's no DENY on this group.
                    	
                    	if (log.isTraceEnabled()) {
                    		log.trace("Found unblocked path to this permission set including a GRANT: [" + activeParentPermissions + "]");
                    	}
                    	
                        return true;
                    }

                    if (! parentPermissionsContainsDeny) {
                        // there's no blocking deny, so recursively check to see if the parent has an unblocked path to a grant


                        boolean parentHasUnblockedPathToGrant = hasUnblockedPathToGrant(service, parentPrincipal, owner, activity, target, seenGroups);
                        if (parentHasUnblockedPathToGrant) {
                            return true;
                        }


                        // parent didn't have a path to grant.  Fall through and try another parent if any
                    }


                }
            } catch (Exception e) {
                // problem evaluating this path, but let's not let it stop
                // us from exploring other paths.  Though a portion of the
                // group structure is broken, permission may be granted by
                // an unbroken portion
                log.error("Error evaluating permissions of parent group [" + parentGroup + "]", e);
            }

        }
        return false;
    }

    /**
     * Returns a Set containing those IPermission instances where the present
     * date is neither after the permission expiration if present nor before
     * the permission start date if present.
     * @param perms
     * @return potentially empty non-null Set of active permissions.
     */
    private Set<IPermission> activePermissions(final IPermission[] perms) {
        Date now = new Date();

        Set<IPermission> activePermissions = new HashSet<IPermission>(1);

        for (int i = 0; i < perms.length; i++) {
            IPermission p = perms[i];

            if (
                (p.getEffective() == null || ! p.getEffective().after(now))
                &&
                (p.getExpires() == null || p.getExpires().after(now))
               ) {

                activePermissions.add(p);


            }

        }

        return activePermissions;


    }

    /**
     * Returns true if a set of IPermission instances contains a permission of
     * the specified type.
     * False otherwise.
     * @param permissions
     * @return true if the set contains a permission of the sought type, false otherwise
     * @throws IllegalArgumentException if input set or type is null.
     */
    private boolean containsType(final Set<IPermission> permissions, final String soughtType) {

        if (permissions == null) {
            throw new IllegalArgumentException("Cannot check null set for contents.");
        }

        if (soughtType == null) {
            throw new IllegalArgumentException("Cannot search for type null.");
        }

        for (Iterator<IPermission> permissionIter = permissions.iterator(); permissionIter.hasNext(); ) {
            IPermission permission = permissionIter.next();

            if (permission != null && soughtType.equals(permission.getType())) {
                return true;
            }
        }

        return false;

    }

}
