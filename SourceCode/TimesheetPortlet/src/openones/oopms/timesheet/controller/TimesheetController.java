/**
 * 
 */
package openones.oopms.timesheet.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;

import openones.oopms.timesheet.dao.TimesheetDao;
import openones.oopms.timesheet.dao.UserDao;
import openones.oopms.timesheet.form.LoginForm;
import openones.oopms.timesheet.form.TimesheetForm;
import openones.oopms.timesheet.model.Developer;
import openones.oopms.timesheet.model.Project;
import openones.oopms.timesheet.model.Timesheet;
import openones.oopms.timesheet.model.Typeofwork;
import openones.oopms.timesheet.model.Workproduct;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

/**
 * Controller for handling all action related to Timesheet System
 * 
 * @author Truong
 * 
 */
@Controller
@RequestMapping("VIEW")
public class TimesheetController {
    // Declare static variables
    // List process of timesheet
    private static List<openones.oopms.timesheet.model.Process> processList = new ArrayList<openones.oopms.timesheet.model.Process>();
    // List work types of timesheet
    private static List<Typeofwork> towList = new ArrayList<Typeofwork>();
    // List work product of timesheet
    private static List<Project> projectList = new ArrayList<Project>();
    // Developer object
    private Developer user = new Developer();
    // Map project
    Map<String, String> projectMap;
    // Map Type of work
    Map<String, String> towMap;
    // Map Process
    Map<String, String> processMap;
    // Map Work Product
    Map<String, String> workProductMap;
    // List update timesheet
    List<Timesheet> updateTimesheetList;
    // List delete timesheet
    List<Timesheet>deleteTimesheetList;
    /** Logger for logging. */
    private static Logger log = Logger.getLogger(TimesheetController.class);

    /**
     * Default screen. If user is "guest" (or null), display Login form. Otherwise (authenticated), display the
     * 
     * @return name of view which is the name of the JSP page.
     */
    private boolean error = false;

    @RequestMapping
    public String initScreen(RenderRequest request) {
        log.debug("initScreen.START");
        // Get logon user
        if (!error) {
            return "login";

        } else {
            return "AddTimesheet";
        }

    }

    /**
     * Create bean for login form.
     * 
     * @return Form bean for UI.
     */
    @ModelAttribute("loginForm")
    public LoginForm getCommandObject() {
        log.debug("getCommandObject.START");
        LoginForm formBean = new LoginForm();
        formBean.setUsername("TRUONGMH");
        return formBean;
    }

    /**
     * Process submitted form by clicking "Login" button.
     * 
     * @param formBean
     *            bean captures input data
     * @param result
     *            result of binding data
     * @param status
     *            status of session
     * @param response
     *            response of action
     */
    @ActionMapping(params = "action=init")
    public void processLogin(LoginForm formBean, BindingResult result, SessionStatus status, ActionResponse response) {
        log.debug("processLogin.START");
        log.debug("username=" + formBean.getUsername());
        // session.setAttribute("user", formBean);

        UserDao userDao = new UserDao();

        user = userDao.authenticate(formBean.getUsername(), formBean.getPassword());

        if (user != null) {
            // Prepare parameter to render phase
            response.setRenderParameter("action", "init");
        } else {
            result.rejectValue("username", "error");
            log.error("Error in binding result:" + result.getErrorCount());
        }
    }

    /**
     * Process after the action "login" (method "processLogin") is executed.
     * 
     * @return view "ViewDefectList" which next page "ViewDefectList.jsp" will displayed
     */
    @RenderMapping(params = "action=init")
    public ModelAndView postLogin(TimesheetForm formBean, RenderRequest request) {
        log.debug("postLogin.START");
        // Declare view for this render
        ModelAndView mav = new ModelAndView("Timesheet"); // display Timesheet.jsp
        log.debug("Timesheet Form value :" + formBean.getProjectDefault() + " status:" + formBean.getStatus());
        TimesheetDao timesheetDao = new TimesheetDao();
        projectMap = new LinkedHashMap<String, String>();

        // Get project List from database
        projectList = timesheetDao.getProjectList(String.valueOf(user.getDeveloperId()));
        projectMap.put("All", "All");
        for (int i = 0; i < projectList.size(); i++) {
            projectMap.put(projectList.get(i).getProjectId().toString(), projectList.get(i).getName());
        }
        // Set project list to form
        formBean.setProjectMap(projectMap);

        // Set information of user to session
        PortletSession session = request.getPortletSession();
        session.setAttribute("USERID", user.getDeveloperId(), PortletSession.APPLICATION_SCOPE);
        session.setAttribute("USER", user.getAccount(), PortletSession.APPLICATION_SCOPE);

        // Set default value for timesheet.jsp
        mav.addObject("projectMap", formBean.getProjectMap());
        mav.addObject("projectDefault", formBean.getProjectDefault());

        // Search all timesheet record from database
        TimesheetDao timeDao = new TimesheetDao();
        List<Timesheet> timesheetList = timeDao.getTimesheetList(String.valueOf(user.getDeveloperId()), "All", "", "",
                "All");

        // Get dropdown list from database
        processList = timeDao.getProcessList();
        towList = timeDao.getTOWList();

        timesheetList = prepareTimesheetList(timesheetList);

        // Add object timesheetList to request
        mav.addObject("timesheetList", timesheetList);
        // Return to jsp
        return mav;
    }

    private List<Timesheet> prepareTimesheetList(List<Timesheet> timesheetList) {
        // Set value of dropdownlist to table timesheet
        int processCode = 0;
        int towCode = 0;

        for (int i = 0; i < timesheetList.size(); i++) {
            processCode = Integer.parseInt(timesheetList.get(i).getProcessId().toString());
            towCode = Integer.parseInt(timesheetList.get(i).getTowId().toString());
            timesheetList.get(i).setProcessName(processList.get(processCode - 1).getName());
            timesheetList.get(i).setTowName(towList.get(towCode - 1).getName());
            timesheetList.get(i).setDurationString(timesheetList.get(i).getDuration().toString());
            timesheetList.get(i).setProjectName(timesheetList.get(i).getProject().getName());
        }
        return timesheetList;
    }

    /**
     * Create bean for timesheet form.
     * 
     * @return Form bean for UI.
     */

    @ModelAttribute("timesheetForm")
    public TimesheetForm getCommandObject2() {
        log.debug("getCommandObject.START");
        // Decalre Bean and Dao
        TimesheetForm formBean = new TimesheetForm();

        return formBean;
    }

    @ActionMapping(params = "action=searchTimesheet")
    public void processSearchTimesheet(TimesheetForm formBean, BindingResult result, SessionStatus status,
            ActionResponse response) {
        log.debug("processSearchTimesheet.START");
        if (result.hasErrors()) {
            log.debug("search timesheet error");
        } else {
            log.debug("Timesheet Form value :" + formBean.getProjectDefault() + " status:" + formBean.getStatus());
            response.setRenderParameter("action", "searchTimesheet");
        }

    }

    /**
     * Process after the action "searchTimesheet" (method "processsearchTimesheet") is executed.
     * 
     * @return view "Timesheet.jsp" which next page "Timesheet.jsp" will displayed
     */
    @RenderMapping(params = "action=searchTimesheet")
    public ModelAndView postTimesheet(TimesheetForm formBean, RenderRequest request) {
        ModelAndView mav = new ModelAndView("Timesheet"); // display Timesheet.jsp
        TimesheetDao timesheetDao = new TimesheetDao();
        // projectMap = new LinkedHashMap<String, String>();
        //
        // // Get project List from database
        // projectList = timesheetDao.getProjectList(String.valueOf(user.getDeveloperId()));
        // projectMap.put("All", "All");
        // for (int i = 0; i < projectList.size(); i++) {
        // projectMap.put(projectList.get(i).getProjectId().toString(), projectList.get(i).getName());
        // }
        // Set project list to form
        formBean.setProjectMap(projectMap);

        // Set select value to jsp
        mav.addObject("projectMap", formBean.getProjectMap());
        mav.addObject("projectDefault", formBean.getProjectDefault());
        TimesheetDao timeDao = new TimesheetDao();
        // Get timesheet List from database with value from form
        List<Timesheet> timesheetList = timeDao.getTimesheetList(String.valueOf(user.getDeveloperId()),
                formBean.getProjectDefault(), formBean.getFromDate(), formBean.getToDate(), formBean.getStatus());

        // Set value of dropdownlist to table timesheet

        if (timesheetList != null) {
            int size = timesheetList.size();
            if (size > 0) {

                timesheetList = prepareTimesheetList(timesheetList);
            }
        }

        mav.addObject("timesheetList", timesheetList);

        return mav;
    }

    /**
     * Create bean for timesheet form.
     * 
     * @return Form bean for UI.
     */

    @ModelAttribute("addTimesheetForm")
    public TimesheetForm getCommandObject3() {

        // Declare Bean and Dao
        TimesheetForm formBean = new TimesheetForm();

        return formBean;
    }

    @ActionMapping(params = "action=goAddTimesheet")
    public void processAddTimesheet(TimesheetForm formBean, BindingResult result, SessionStatus status,
            ActionResponse response) {
        log.debug("processSearchTimesheet.START");
        if (result.hasErrors()) {
            log.debug("search timesheet error");
        } else {
            log.debug("Timesheet Form value :" + formBean.getProjectDefault() + " status:" + formBean.getStatus());
            response.setRenderParameter("action", "goAddTimesheet");
        }

    }

    @RenderMapping(params = "action=goAddTimesheet")
    public ModelAndView postAddTimesheet(TimesheetForm formBean, RenderRequest request) {
        List<Timesheet> timesheetList = new ArrayList<Timesheet>();
        Timesheet timesheet;
        int numRecord = 20;
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Calendar cal = Calendar.getInstance();

        for (int i = 0; i < numRecord - 1; i++) {
            timesheet = new Timesheet();
            if ((i % 2) == 0) {
                if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
                    cal.add(Calendar.DATE, -4);
                } else if (cal.get(Calendar.DAY_OF_WEEK) == 2) {
                    cal.add(Calendar.DATE, -3);
                } else {
                    cal.add(Calendar.DATE, -1);
                }
            }

            timesheet.setOccurDateString(sdf.format(cal.getTime()));
            timesheetList.add(timesheet);
        }

        formBean.setTimesheetList(timesheetList);
        log.debug("postAdd.START");
        // Declare view for this render
        ModelAndView mav = new ModelAndView("AddTimesheet"); // display AddTimesheet.jsp
        prepareMaps();

        formBean.setProjectMap(projectMap);

        // Set default value for timesheet.jsp
        mav.addObject("projectMap", formBean.getProjectMap());
        mav.addObject("processMap", processMap);
        mav.addObject("towMap", towMap);

        // Add object timesheetList to request
        mav.addObject("timesheetList", formBean.getTimesheetList());
        // Return to jsp
        return mav;
    }

    private void prepareMaps() {
        // Create towMap, processMap, workProductMap
        towMap = new LinkedHashMap<String, String>();
        towMap.put("", "");
        for (int i = 0; i < towList.size(); i++) {
            towMap.put(towList.get(i).getTowId().toString(), towList.get(i).getName());
        }
        processMap = new LinkedHashMap<String, String>();
        processMap.put("", "");
        for (int i = 0; i < processList.size(); i++) {
            processMap.put(processList.get(i).getProcessId().toString(), processList.get(i).getName());
        }

        // Set project list to form
        projectMap.remove("All");

    }

    @ActionMapping(params = "action=addTimesheet")
    public void processAddTimesheetToDB(TimesheetForm formBean, BindingResult result, SessionStatus status,
            ActionResponse response) throws IOException, ParseException {
        Timesheet timesheet;
        error = false;
        List<Timesheet> timesheetList = new ArrayList<Timesheet>();
        List<Timesheet> tempList = formBean.getTimesheetList();
        for (int i = 0; i < tempList.size(); i++) {
            timesheet = new Timesheet();
            timesheet = tempList.get(i);

            if (!"".equals(timesheet.getOccurDateString()) && !"".equals(timesheet.getProjectName())
                    && !"".equals(timesheet.getProcessName()) && !"".equals(timesheet.getTowName())
                    && !"".equals(timesheet.getDurationString()) && !"".equals(timesheet.getDescription())
                    && !timesheet.getDescription().isEmpty()) {

                timesheetList.add(timesheet);

            }
        }

        TimesheetDao timesheetDao = new TimesheetDao();
        timesheetDao.insertTimesheet(timesheetList, user.getDeveloperId());

        response.setRenderParameter("action", "init");

    }

    @ActionMapping(params = "action=goUpdateTimesheet")
    public void processGoUpdateTimesheet(TimesheetForm formBean, BindingResult result, SessionStatus status,
            ActionResponse response) {
        updateTimesheetList = new ArrayList<Timesheet>();
        List<Timesheet> tempList = new ArrayList<Timesheet>();
        tempList = formBean.getTimesheetList();
        for (int i = 0; i < tempList.size(); i++) {
            if (tempList.get(i).getTimesheetId() != null) {
                updateTimesheetList.add(tempList.get(i));
            }
        }
        response.setRenderParameter("action", "goUpdateTimesheet");

    }

    /**
     * Process after the action "updateTimesheet" (method "processsearchTimesheet") is executed.
     * 
     * @return view "Timesheet.jsp" which next page "Timesheet.jsp" will displayed
     */
    @RenderMapping(params = "action=goUpdateTimesheet")
    public ModelAndView postUpdateTimesheet(TimesheetForm formBean, RenderRequest request) {
       
        ModelAndView mav = new ModelAndView("UpdateTimesheet"); // display Timesheet.jsp
        List<Timesheet> timesheetList = new ArrayList<Timesheet>();
        Timesheet timesheet;
        TimesheetDao timeDao = new TimesheetDao();
        for (int i = 0; i < updateTimesheetList.size(); i++) {

            timesheet = timeDao.getTimesheetById(updateTimesheetList.get(i).getTimesheetId());
            timesheetList.add(timesheet);
        }

        timesheetList = prepareTimesheetList(timesheetList);
        formBean.setTimesheetList(timesheetList);
        prepareMaps();
        mav.addObject("projectMap", projectMap);
        mav.addObject("processMap", processMap);
        mav.addObject("towMap", towMap);
       
        // Add object timesheetList to request
        mav.addObject("timesheetList", timesheetList);
        updateTimesheetList = timesheetList;
        // Return to jsp
        return mav;

    }
    
    @ActionMapping(params = "action=updateTimesheet")
    public void processUpdateTimesheet(TimesheetForm formBean, BindingResult result, SessionStatus status,
            ActionResponse response) throws ParseException {
        List<Timesheet> tempList = new ArrayList<Timesheet>();
        tempList = formBean.getTimesheetList();
        for (int i = 0; i < tempList.size(); i++) {
            System.out.println("occurDateString :"+ tempList.get(i).getOccurDateString()+"projId "
                    + tempList.get(i).getProject().getProjectId()+ "duration :" + tempList.get(i).getDuration()+"desc : "+tempList.get(i).getDescription());
            updateTimesheetList.get(i).setOccurDateString(tempList.get(i).getOccurDateString());
            updateTimesheetList.get(i).setProjectName(tempList.get(i).getProject().getProjectId().toString());
            updateTimesheetList.get(i).setProcessId(new BigDecimal(tempList.get(i).getProcessId().toString()));
            updateTimesheetList.get(i).setTowId(new BigDecimal(tempList.get(i).getTowId().toString()));
            updateTimesheetList.get(i).setDuration(new BigDecimal(tempList.get(i).getDuration().toString()));
            updateTimesheetList.get(i).setDescription(tempList.get(i).getDescription());
            
           TimesheetDao timeDao = new TimesheetDao();
           timeDao.updateTimesheet(updateTimesheetList, user.getDeveloperId());
           
           
        }
        response.setRenderParameter("action", "init");

    }
    
    @ActionMapping(params = "action=deleteTimesheet")   
    public void processdeleteTimesheet(TimesheetForm formBean, BindingResult result, SessionStatus status,
            ActionResponse response) throws ParseException {
        deleteTimesheetList = new ArrayList<Timesheet>();
        List<Timesheet> tempList = new ArrayList<Timesheet>();
        tempList = formBean.getTimesheetList();
        for (int i = 0; i < tempList.size(); i++) {
            if (tempList.get(i).getTimesheetId() != null) {
                deleteTimesheetList.add(tempList.get(i));
            }
        }
        TimesheetDao timeDao = new TimesheetDao();
        timeDao.deleteTimesheet(deleteTimesheetList, user.getDeveloperId());
        response.setRenderParameter("action", "init");

    }
}