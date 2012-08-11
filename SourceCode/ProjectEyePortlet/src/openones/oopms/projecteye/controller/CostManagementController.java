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

import openones.oopms.projecteye.dao.CostDao;
import openones.oopms.projecteye.form.CostManagementForm;
import openones.oopms.projecteye.form.CreateCostTypeForm;
import openones.oopms.projecteye.form.CreateDailyExpenseForm;
import openones.oopms.projecteye.form.CreateExceptionalDeductForm;
import openones.oopms.projecteye.form.CreateExceptionalExpenseForm;
import openones.oopms.projecteye.form.CreateOneTimeExpenseForm;
import openones.oopms.projecteye.form.CreateProjectForm;
import openones.oopms.projecteye.form.DailyExpense;
import openones.oopms.projecteye.form.DeleteCostForm;
import openones.oopms.projecteye.form.UpdateCostTypeForm;
import openones.oopms.projecteye.form.UpdateDailyExpenseForm;
import openones.oopms.projecteye.form.UpdateOneTimeExpenseForm;
import openones.oopms.projecteye.model.Developer;
import openones.oopms.projecteye.model.OopmsCostDailyExpense;
import openones.oopms.projecteye.model.OopmsCostOneTimeExpense;
import openones.oopms.projecteye.model.OopmsCostType;
import openones.oopms.projecteye.utils.AppUtil;
import openones.oopms.projecteye.utils.Constant;
import openones.oopms.projecteye.utils.CostUtil;
import openones.oopms.projecteye.utils.HTMLTag;

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
public class CostManagementController {

	Developer user = new Developer();
	/** Logger for logging. */
	private static Logger log = Logger
			.getLogger(CostManagementController.class);

	@RenderMapping(params = "action=GoCreateOneTimeExpense")
	public ModelAndView postGoCreateOneTimeExpense(RenderRequest request) {
		log.debug("post GoCreateOneTimeExpense.START");
		ModelAndView mav = new ModelAndView("CreateOneTimeExpense",
				"CreateOneTimeExpenseForm", new CreateOneTimeExpenseForm());
		String projectId = request.getParameter("projectId");
		log.debug("project ID : " + projectId);
		mav.addObject("projectId", projectId);
		return mav;
	}

	@RenderMapping(params = "action=GoCreateDailyExpense")
	public ModelAndView postGoCreateDailyExpense(RenderRequest request) {
		log.debug("post GoCreateDailyExpense.START");
		String projectId = request.getParameter("projectId");
		ModelAndView mav = new ModelAndView("CreateDailyExpense",
				"CreateDailyExpenseForm", new CreateDailyExpenseForm());
		CostDao cDao = new CostDao();
		List<OopmsCostType> costTypeList = cDao.getCostTypeList(projectId);
		Map<String, String> costTypeMap = new LinkedHashMap<String, String>();
		costTypeMap.put(null, " ");
		if (costTypeList != null) {
			for (int i = 0; i < costTypeList.size(); i++) {
				costTypeMap.put(costTypeList.get(i).getOopmsCostTypeId()
						.toString(), costTypeList.get(i).getName());
			}
		}
		log.debug("project ID : " + projectId);
		mav.addObject("projectId", projectId);
		mav.addObject("costType", costTypeMap);
		return mav;
	}

	@RenderMapping(params = "action=GoCreateExceptionalExpense")
	public ModelAndView postGoCreateExceptionalExpense(RenderRequest request) {
		log.debug("post GoCreateExceptionalExpense.START");
		CreateExceptionalExpenseForm form = new CreateExceptionalExpenseForm();
		form.setAffectTo(Constant.ExceptinalCostEffectToType);
		form.setAdditionEffect(Constant.ExceptinalRationCostEffectType);
		ModelAndView mav = new ModelAndView("CreateExceptionalExpense",
				"CreateExceptionalExpenseForm", form);
		String projectId = request.getParameter("projectId");
		CostDao cDao = new CostDao();
		List<OopmsCostType> costTypeList = cDao.getCostTypeList(projectId);
		List<OopmsCostDailyExpense> dailyExpenseList = cDao
				.getDailyExpenseList(projectId);
		List<DailyExpense> dailyExpenseListView = CostUtil
				.getDailyExpenseListView(dailyExpenseList);
		mav.addObject("CostTypeList", costTypeList);
		mav.addObject("DailyExpenseList", dailyExpenseListView);
		log.debug("project ID : " + projectId);
		mav.addObject("projectId", projectId);
		return mav;
	}

	@RenderMapping(params = "action=GoCreateExceptionalDeduct")
	public ModelAndView postGoCreateExceptionalDeduct(RenderRequest request) {
		log.debug("post GoCreateExceptionalDeduct.START");
		CreateExceptionalDeductForm form = new CreateExceptionalDeductForm();
		form.setAffectTo(Constant.ExceptinalCostEffectToType);
		form.setAdditionEffect(Constant.ExceptinalRationCostEffectType);
		ModelAndView mav = new ModelAndView("CreateExceptionalDeduct",
				"CreateExceptionalDeductForm", form);
		String projectId = request.getParameter("projectId");
		CostDao cDao = new CostDao();
		List<OopmsCostType> costTypeList = cDao.getCostTypeList(projectId);
		List<OopmsCostDailyExpense> dailyExpenseList = cDao
				.getDailyExpenseList(projectId);
		List<DailyExpense> dailyExpenseListView = CostUtil
				.getDailyExpenseListView(dailyExpenseList);
		mav.addObject("CostTypeList", costTypeList);
		mav.addObject("DailyExpenseList", dailyExpenseListView);
		log.debug("project ID : " + projectId);
		mav.addObject("projectId", projectId);
		return mav;
	}

	@RenderMapping(params = "action=GoCreateCostType")
	public ModelAndView postGoCreateCostType(RenderRequest request) {
		log.debug("post GoCreateOneTimeExpense.START");
		ModelAndView mav = new ModelAndView("CreateCostType",
				"CreateCostTypeForm", new CreateCostTypeForm());
		String projectId = request.getParameter("projectId");
		log.debug("project ID : " + projectId);
		mav.addObject("projectId", projectId);
		return mav;
	}

	@ActionMapping(params = "action=ViewExpense")
	public void processViewExpense(CostManagementForm formBean,
			BindingResult result, SessionStatus status, ActionResponse response) {
		log.debug("post ViewExpense.START");
		response.setRenderParameter("action", "GoCostManagement");
		response.setRenderParameter("projectId", formBean.getProjectId());
		response.setRenderParameter("viewDate", formBean.getViewDate());
	}

	@ActionMapping(params = "action=RemoveOneTimeExpense")
	public void processRemoveOneTimeExpense(DeleteCostForm formBean,
			BindingResult result, SessionStatus status, ActionResponse response) {
		log.debug("post RemoveOneTimeExpense.START");
		CostDao cDao = new CostDao();
		cDao.deleteOneTimeExpense(formBean.getOopmsCostOneTimeExpenseId());
		response.setRenderParameter("action", "GoCostManagement");
		response.setRenderParameter("projectId", formBean.getProjectId());
	}

	@ActionMapping(params = "action=RemoveDailyExpense")
	public void processRemoveDailyExpense(DeleteCostForm formBean,
			BindingResult result, SessionStatus status, ActionResponse response) {
		log.debug("post RemoveDailyExpense.START");
		CostDao cDao = new CostDao();
		cDao.deleteDailyExpense(formBean.getOopmsCostDailyExpenseId());
		response.setRenderParameter("action", "GoCostManagement");
		response.setRenderParameter("projectId", formBean.getProjectId());
	}

	@ActionMapping(params = "action=RemoveExceptionalCost")
	public void processRemoveExceptionalCost(DeleteCostForm formBean,
			BindingResult result, SessionStatus status, ActionResponse response) {
		log.debug("post RemoveExceptionalCost.START");
		CostDao cDao = new CostDao();
		cDao.deleteExceptionalCost(formBean.getOopmsExceptionalCostId());
		response.setRenderParameter("action", "GoCostManagement");
		response.setRenderParameter("projectId", formBean.getProjectId());
	}

	@ActionMapping(params = "action=RemoveCostType")
	public void processRemoveCostType(DeleteCostForm formBean,
			BindingResult result, SessionStatus status, ActionResponse response) {
		log.debug("post RemoveCostType.START");
		CostDao cDao = new CostDao();
		String costTypeUsed = cDao.checkCostTypeUsed(formBean
				.getOopmsCostTypeId());
		log.debug("costTypeUsed : " + costTypeUsed);
		if (Constant.CostTypeNotUsed.equals(costTypeUsed)) {
			cDao.deleteCostType(formBean.getOopmsCostTypeId());
		} else {
			response.setRenderParameter("deleteCostTypeFlag", costTypeUsed);
			response.setRenderParameter("deletingOopmsCostTypeId",
					formBean.getOopmsCostTypeId());
		}
		response.setRenderParameter("action", "GoCostManagement");
		response.setRenderParameter("projectId", formBean.getProjectId());
	}

	@ActionMapping(params = "action=ForcedRemoveCostType")
	public void processForcedRemoveCostType(DeleteCostForm formBean,
			BindingResult result, SessionStatus status, ActionResponse response) {
		log.debug("post ForcedRemoveCostType.START");
		CostDao cDao = new CostDao();
		cDao.forcedDeleteCostType(formBean.getOopmsCostTypeId());
		response.setRenderParameter("deleteCostTypeFlag", Constant.CostTypeNotUsed);
		response.setRenderParameter("action", "GoCostManagement");
		response.setRenderParameter("projectId", formBean.getProjectId());
	}
	
	@RenderMapping(params = "action=GoUpdateOneTimeExpense")
	public ModelAndView postGoUpdateOneTimeExpense(RenderRequest request) {
		log.debug("post GoUpdateOneTimeExpense.START");
		String oopmsCostOneTimeExpenseId = request.getParameter("oopmsCostOneTimeExpenseId");
		CostDao cDao = new CostDao();
		OopmsCostOneTimeExpense oneTimeExpense = cDao.getOneTimeExpense(oopmsCostOneTimeExpenseId);
		UpdateOneTimeExpenseForm form = new UpdateOneTimeExpenseForm();
		form.setCost(String.valueOf(oneTimeExpense.getCost()));
		form.setName(oneTimeExpense.getName());
		form.setDescription(HTMLTag.replaceHTMLTag(oneTimeExpense.getDescription()));
		form.setDate(AppUtil.getDateAsFormat(oneTimeExpense.getOccurDate(), Constant.DateFormat));
		ModelAndView mav = new ModelAndView("UpdateOneTimeExpense",
				"UpdateOneTimeExpenseForm", form);
		String projectId = request.getParameter("projectId");
		log.debug("project ID : " + projectId);
		mav.addObject("projectId", projectId);
		mav.addObject("oopmsCostOneTimeExpenseId", oopmsCostOneTimeExpenseId);
		return mav;
	}
	
	@RenderMapping(params = "action=GoUpdateDailyExpense")
	public ModelAndView postGoUpdateDailyExpense(RenderRequest request) {
		log.debug("post GoUpdateDailyExpense.START");
		String oopmsCostDailyExpenseId = request.getParameter("oopmsCostDailyExpenseId");
		CostDao cDao = new CostDao();
		OopmsCostDailyExpense dailyExpense = cDao.getDailyExpense(new BigDecimal(oopmsCostDailyExpenseId));
		UpdateDailyExpenseForm form = new UpdateDailyExpenseForm();
		form.setCost(String.valueOf(dailyExpense.getCost()));
		form.setName(dailyExpense.getName());
		form.setDescription(HTMLTag.replaceHTMLTag(dailyExpense.getDescription()));
		form.setStartDate(AppUtil.getDateAsFormat(dailyExpense.getStartDate(), Constant.DateFormat));
		form.setEndDate(AppUtil.getDateAsFormat(dailyExpense.getEndDate(), Constant.DateFormat));
		if(dailyExpense.getOopmsCostTypeId()!=null) {
			form.setCostType_SelectedValue(String.valueOf(dailyExpense.getOopmsCostTypeId()));
		}
		form.setDays(CostUtil.getDaysUsed(dailyExpense.getDateUsed()));
		ModelAndView mav = new ModelAndView("UpdateDailyExpense",
				"UpdateDailyExpenseForm", form);
		String projectId = request.getParameter("projectId");
		List<OopmsCostType> costTypeList = cDao.getCostTypeList(projectId);
		Map<String, String> costTypeMap = new LinkedHashMap<String, String>();
		costTypeMap.put(null, " ");
		if (costTypeList != null) {
			for (int i = 0; i < costTypeList.size(); i++) {
				costTypeMap.put(costTypeList.get(i).getOopmsCostTypeId()
						.toString(), costTypeList.get(i).getName());
			}
		}
		log.debug("project ID : " + projectId);
		mav.addObject("projectId", projectId);
		mav.addObject("oopmsCostDailyExpenseId", oopmsCostDailyExpenseId);
		mav.addObject("costType", costTypeMap);
		return mav;
	}
	
	@RenderMapping(params = "action=GoUpdateCostType")
	public ModelAndView postGoUpdateCostType(RenderRequest request) {
		log.debug("post GoUpdateCostType.START");
		String oopmsCostTypeId = request.getParameter("oopmsCostTypeId");
		UpdateCostTypeForm form = new UpdateCostTypeForm();
		CostDao cDao = new CostDao();
		OopmsCostType costType = cDao.getCostType(new BigDecimal(oopmsCostTypeId));
		form.setName(costType.getName());
		form.setDescription(HTMLTag.replaceHTMLTag(costType.getDescription()));
		ModelAndView mav = new ModelAndView("UpdateCostType",
				"UpdateCostTypeForm", form);
		String projectId = request.getParameter("projectId");
		log.debug("project ID : " + projectId);
		mav.addObject("oopmsCostTypeId", oopmsCostTypeId);
		mav.addObject("projectId", projectId);
		return mav;
	}
}
