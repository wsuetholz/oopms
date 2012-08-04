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
package openones.oopms.projecteye.controller;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;

import openones.oopms.projecteye.dao.MilestoneDao;
import openones.oopms.projecteye.dao.WorkOrderDao;
import openones.oopms.projecteye.form.CreateDeliverableForm;
import openones.oopms.projecteye.form.CreateStageForm;
import openones.oopms.projecteye.form.UpdateStageForm;
import openones.oopms.projecteye.model.Developer;
import openones.oopms.projecteye.model.Milestone;
import openones.oopms.projecteye.model.Module;
import openones.oopms.projecteye.model.Ncconstant;
import openones.oopms.projecteye.model.Project;
import openones.oopms.projecteye.model.Stage;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

/**
 * @author HaiTCT
 */
@Controller
@RequestMapping("VIEW")
public class WorkOrderController {

	Developer user = new Developer();
	/** Logger for logging. */
	private static Logger log = Logger.getLogger(WorkOrderController.class);

	@ActionMapping(params = "action=GoUpdateStage")
	public void processGoUpdateStage(BindingResult result,
			SessionStatus status, ActionResponse response) {
		log.debug("process GoUpdateStage.START");
		response.setRenderParameter("action", "GoUpdateStage");
	}

	@RenderMapping(params = "action=GoUpdateStage")
	public ModelAndView postGoUpdateStage(RenderRequest request) {
		log.debug("post GoCreateIssue.START");
		// get stage list
		MilestoneDao mDao = new MilestoneDao();
		String stageId = request.getParameter("stageId");
		Milestone stage = mDao.getStage(stageId);
		UpdateStageForm formBean = new UpdateStageForm();
//		formBean.setPlannedStartDate(stage.getBaseStartDate());
//		formBean.setRePlannedStartDate(stage.getPlanStartDate());
//		formBean.setActualStartDate(stage.getActualStartDate());
		formBean.setPlannedEndDate(stage.getBaseFinishDate());
		formBean.setRePlannedEndDate(stage.getPlanFinishDate());
		formBean.setActualEndDate(stage.getActualFinishDate());
		formBean.setDescription(stage.getDescription());
		formBean.setMilestone(stage.getMilestone());
		formBean.setStage(stage.getName());
		ModelAndView mav = new ModelAndView("UpdateStage","UpdateStageForm",formBean);
		String projectId = request.getParameter("projectId");
		log.debug("project ID la " + projectId);
		mav.addObject("projectId", projectId);
		mav.addObject("stageId", stageId);
		return mav;
	}

	@ActionMapping(params = "action=GoCreateDeliverable")
	public void processGoCreateDeliverable(BindingResult result,
			SessionStatus status, ActionResponse response) {
		log.debug("process GoCreateDeliverable.START");
		response.setRenderParameter("action", "GoCreateDeliverable");
	}

	@RenderMapping(params = "action=GoCreateDeliverable")
	public ModelAndView postGoCreateDeliverable(RenderRequest request) {
		log.debug("post GoCreateDeliverable.START");
		String projectId = request.getParameter("projectId");
		// get status list
		WorkOrderDao woDao = new WorkOrderDao();
		List<Ncconstant> statusList = woDao.getStatusList();
		Map<String, String> statusMap = new LinkedHashMap<String, String>();
		for (int i = 0; i < statusList.size(); i++) {
			statusMap.put(statusList.get(i).getConstantid().toString(),
					statusList.get(i).getDescription());
		}

		Project project = new Project();
		project.setProjectId(new BigDecimal(projectId));
		List<Module> productList = woDao
				.getUnsetDeliverableProductList(project);
		Map<String, String> productMap = new LinkedHashMap<String, String>();
		if (productList.size() != 0) {

			for (int i = 0; i < productList.size(); i++) {
				productMap.put(productList.get(i).getModuleId().toString(),
						productList.get(i).getName());
			}
		} else {

		}
		CreateDeliverableForm projectFormBean = new CreateDeliverableForm();
		request.setAttribute("CreateDeliverableForm", projectFormBean);
		ModelAndView mav = new ModelAndView("CreateDeliverable");
		mav.addObject("status", statusMap);
		mav.addObject("deliverable", productMap);
		log.debug("project ID la " + projectId);
		mav.addObject("projectId", projectId);
		return mav;
	}

}
