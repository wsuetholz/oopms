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
package openones.oopms.requirement.validator;


import openones.oopms.requirement.form.RequirementAddForm;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Kenda
 */
@Component("AddDefectValidator")
public class RequiremendAddValidator extends BaseValidator implements Validator {

    /**
     * [Explain the description for this method here].
     * @param arg0
     * @return
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    public boolean supports(Class<?> clazz) {
        return RequirementAddForm.class.isAssignableFrom(clazz);
    }

    /**
     * [Explain the description for this method here].
     * @param arg0
     * @param arg1
     * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
     */
    public void validate(Object target, Errors errors) {
        log.debug("validate.START");

        //RequirementAddForm bean = (RequirementAddForm) target;
        
        log.debug("requirementNameValidator");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "requirementName", "NotEmpty.req.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "reqSize", "NotEmpty.req.size");

//        if (bean.getTitle().length() > 225 || bean.getTitle().length() < 10) {
//            errors.rejectValue("title", "FieldLength.defect.title");
//        }
//        
//        if (bean.getSrs().length() > 1800) {
//            errors.rejectValue("description", "FieldLength.defect.description");
//        }
    }

}