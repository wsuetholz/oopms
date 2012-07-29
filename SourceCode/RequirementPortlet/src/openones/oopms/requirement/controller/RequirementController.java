package openones.oopms.requirement.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;

import openones.oopms.requirement.dao.RequirementDao;
import openones.oopms.requirement.form.RequirementForm;
import openones.oopms.requirement.model.Requirements;
import openones.oopms.requirement.model.Project;
import openones.oopms.requirement.model.Timesheet;
import openones.portlet.PortletSupport;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

@Controller
@RequestMapping("VIEW")
public class RequirementController {
    
 // Declare static variables
        
    // Map project
    Map<String, String> projectMap;    
    private static List<Project> projectList = new ArrayList<Project>();
    
    // List update Requirements
    List<Requirements> updateRequirementList;
    // List delete Requirements
    List<Requirements>deleteRequirementList;
    // List Requirements
    private List<Requirements> requirementList;
    
    /** Logger for logging. */  
    private static Logger log = Logger.getLogger(RequirementController.class);
    
    private boolean error = false;
    
    /** 
     * Default screen. 
     * @return name of view which is the name of the JSP page. 
     */  
//    @RequestMapping 
//    public String initScreen(RenderRequest request) {  
//        log.debug("initScreen.START"); 
//        // Display RequirementHome.jsp          
//        return "RequirementHome";  
//    } 
    
    
    /**
     * Create bean for form.
     * @return Form bean for UI.
     */
    @ModelAttribute("RequirementForm")
    public RequirementForm getCommandObject(RenderRequest request) {
        log.debug("getCommandObject.START");                       
               
        RequirementForm formBean = new RequirementForm();
        formBean.setTitle("Requirement Form");                                          
        return formBean;
    }
    
    
    @ActionMapping(params = "action=requirementmanager")
    public void processRequirement(RequirementForm formBean, BindingResult result, SessionStatus status, ActionResponse response) {
        log.debug("processRequirement.START");        
    }
    
    @RenderMapping(params = "action=requirementmanager")
    public ModelAndView postRequirement(RequirementForm formBean, RenderRequest request) {
        log.debug("postRequirementSTART");
        // request.setAttribute("user2", formBean);
        //set portlet session                    
        
        ModelAndView mav = new ModelAndView("RequirementHome");
        RequirementDao requirementDAO = new RequirementDao();
        
        requirementList = requirementDAO.getAllRequirement();
        projectList = requirementDAO.getAllProject();            
        
        //get projectName
        log.debug("projectNameSTART");
        try {
            for (int i = 0; i < requirementList.size(); i++) {
                for (int j = 0; j < projectList.size(); j++) {
                    if (requirementList.get(i).getProjectID().equals(projectList.get(j).getProjectId())) {
                        requirementList.get(i).setProjectName(projectList.get(j).getName());
                    }
                }
            }
        } catch (Exception ex) {
            // TODO: handle exception
            log.debug(requirementList.get(0).getProjectID());
            log.debug(projectList.get(0).getProjectId());
            log.debug(projectList.get(0).getName());
            log.debug(requirementList.get(0).getProjectName());
            log.error("Convert ProcessID to string", ex);
        }
        
        formBean.setRequirementList(requirementList);                
        mav.addObject("requirementList", formBean.getRequirementList());
        
      //set projectMap
        
        Map<String,String> projectMap = new LinkedHashMap<String,String>();
        log.debug("projectmap before:" +projectMap.size());
        projectMap.put("All", "All");
        for (int i=0; i<projectList.size();i++) {
            projectMap.put(projectList.get(i).getProjectId().toString(), projectList.get(i).getName());
        }
        log.debug("projectmap after:" +projectMap.size());
        
        formBean.setProjectMap(projectMap);
        formBean.setProjectDefault("All"); 
        mav.addObject("projectDefault", formBean.getProjectDefault());
        mav.addObject("projectMap", formBean.getProjectMap());
        
        return mav;
    }
              
    @ActionMapping(params = "action=goAddNewRequirementAction2Value")
    public void goAddNewRequirementAction2Value(RequirementForm formBean, BindingResult result, SessionStatus status, ActionResponse response) {
        log.debug("goAddNewRequirementAction2Value.START");  
        if (result.hasErrors()) {
            log.debug("go add requirement error");
        } else {
            log.debug("go add requirement Ok");
            response.setRenderParameter("action", "goAddNewRequirementJSP");
        }
    }
    
    @RenderMapping(params = "action=goAddNewRequirementJSP")
    public ModelAndView postAddTimesheet(RequirementForm formBean, RenderRequest request) {
        log.debug("goAddNewRequirementJSPSTART");                         
        
        ModelAndView mav = new ModelAndView("RequirementAdd");  
        
        //Get project name list
        RequirementDao requirementDAO = new RequirementDao();           
        projectList = requirementDAO.getAllProject();  
        log.debug("projectlist after:" +projectList.size());
        
        Map<String,String> projectMap = new LinkedHashMap<String,String>();
        log.debug("projectmap before:" +projectMap.size());
        projectMap.put("All", "All");
        for (int i=0; i<projectList.size();i++) {
            projectMap.put(projectList.get(i).getProjectId().toString(), projectList.get(i).getName());
        }
        log.debug("projectmap after:" +projectMap.size());
        
        formBean.setProjectMap(projectMap);
        formBean.setProjectDefault("All"); 
        mav.addObject("projectDefault", formBean.getProjectDefault());
        mav.addObject("projectMap", formBean.getProjectMap());
        return mav;
    }

}
