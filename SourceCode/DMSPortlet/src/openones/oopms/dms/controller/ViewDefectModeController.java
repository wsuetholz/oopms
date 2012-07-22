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
package openones.oopms.dms.controller;

import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;

import openones.oopms.dms.form.ViewDefectModeForm;
import openones.oopms.portlet.controller.BaseController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

/**
 * @author Thach.Le
 */
@Controller
@RequestMapping(value = "VIEW")
public class ViewDefectModeController extends BaseController {

    /**
     * Create bean for form.
     * @return Form bean for UI.
     */
    @ModelAttribute("viewDefectMode")
    public ViewDefectModeForm getCommandObject() {
        log.debug("getCommandObject.START");
        ViewDefectModeForm formBean = new ViewDefectModeForm();
        return formBean;
    }
    
    @RenderMapping(params = "action=goViewDefectMode")
    public ModelAndView goViewDefectMode(RenderRequest request, PortletSession session) {
        log.debug("goViewDefectMode.START");

        ModelAndView mav = new ModelAndView("ViewDefectMode"); // display ViewDefectMode.jsp
        
        return mav;
    }
}
