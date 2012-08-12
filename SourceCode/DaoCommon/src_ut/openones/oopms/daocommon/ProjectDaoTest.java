/**
 * Licensed to Open-Ones Group under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Open-Ones Group licenses this file to you under the Apache License,
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
package openones.oopms.daocommon;

import static org.junit.Assert.*;

import java.util.List;

import openones.oopms.entity.Developer;
import openones.oopms.entity.ex.ProjectEx;

import org.junit.Test;

/**
 * @author Open-Ones team
 *
 */
public class ProjectDaoTest {

    /**
     * Test method for {@link openones.oopms.daocommon.ProjectDao#getProjectEx()}.
     */
    @Test
    public void testGetProjectExAll() {
        ProjectDao dao = new ProjectDao();
        
        List<ProjectEx> prjList = dao.getProjectEx(null);
        
        assertNotNull(prjList);
        
        for (int i=0; i < prjList.size(); i++) {
            System.out.println(prjList.get(i).getCode() + ":" + prjList.get(i).getWorkunitid());
        }
    }

    @Test
    public void testGetProjects() {
        ProjectDao dao = new ProjectDao();
        DeveloperDao devDao = new DeveloperDao();
        Developer dev = devDao.getDeveloperByAccount("sysadmin");
        
        List<ProjectEx> prjList = dao.getProjects(dev.getDeveloperId());
        
        assertNotNull(prjList);
        
        for (int i=0; i < prjList.size(); i++) {
            System.out.println(prjList.get(i).getCode() + ":" + prjList.get(i).getWorkunitid());
        }
    }
}
