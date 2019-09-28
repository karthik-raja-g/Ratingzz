package com.ideas2it.ratingsystem.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.ideas2it.ratingsystem.constant.Constant;
import com.ideas2it.ratingsystem.info.EmployeeInfo;
import com.ideas2it.ratingsystem.info.EmployeePaginationInfo;
import com.ideas2it.ratingsystem.service.EmployeeService;
import com.ideas2it.ratingsystem.model.Role;
import com.ideas2it.ratingsystem.service.RoleService;
import org.springframework.web.servlet.ModelAndView;
import com.ideas2it.ratingsystem.model.Employee;

/**
 * TODO Exception Handling, Logger.
 * EmployeeController is a layer stands between jsp and service.
 * This gets the parameters, attributes from jsp and passes to 
 * appropriate methods in service. This controller will initiate CRUD 
 * for Employee module.
 * 
 */
@Controller
public class EmployeeController {
                
    private static final String NEW_EMPLOYEE = "/new-employee";
    private static final String ADD_EMPLOYEE_PAGE = "addemployee";
    private static final String VIEW_EMPLOYEE_PAGE = "viewemployee";
    private static final String EDIT_EMPLOYEE_PAGE = "editEmployee";
    private static final String VIEW_ALL_EMPLOYEE_PAGE = "viewemployees";
    private static final String REDIRECT_VIEW_EMPLOYEE_PAGE = 
            "redirect:/view-employees";
    private static final String ASSING_REPORTING_PERSON_PAGE = 
            "assignReportingPerson";
    private static final String CREATE_EMPLOYEE = "/create-employee";
    private static final String EDIT_EMPLOYEE = "/edit-employee";
    private static final String UPDATE_EMPLOYEE = "/update-employee";
    private static final String VIEW_EMPLOYEE = "/view-employee";
    private static final String VIEW_EMPLOYEES = "/view-employees";
    private static final String DELETE_EMPLOYEE = "/delete-employee";
    private static final String VIEW_EMPLOYEES_PAGE_WISE =
            "/view-employees-page-wise";
    private static final String APP_JSON = "application/json";
    private static final String SELECTED_ROLE = "selectedRole";
    private static final String ROLES = "roles";
    private static final String REPORTING_PERSONS = "reportingPersons";
        
    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private RoleService roleService;
    
    /**
     * Creation of a new employee starts here.
     * An new employeeInfo object will be added to model and sent to jsp.
     *
     * @param model - Used to adding attributes to the model.
     * @return ADD_EMPLOYEE_PAGE - Jsp page name to which to be directed.
     */
    @RequestMapping(value = NEW_EMPLOYEE, method = RequestMethod.GET)
    private String createEmployee(Model model) {
        EmployeeInfo employeeInfo = new EmployeeInfo();
        List<Role> roles = roleService.getAllRoleInformation();
        model.addAttribute(ROLES, roles);
        model.addAttribute(Constant.EMPLOYEE_INFO,employeeInfo);
        return ADD_EMPLOYEE_PAGE;
    }
        
    /**
     * ModelAttribute is received with inputs given by user from form.
     * If created employee is employee or team-lead, 
     * then list of reporting person are retrived and sent to addreportingperson.
     * else if created is manager, only created employee is returned in list
     * and redirected to view employee page.
     *
     * @param roleId - Selected role id for the employee.
     * @param employeeInfo - Object with inputs given by user.
     * @param model - brings object with inputs.
     * @return String pageName - if employee or tl created 
     *              , then assignReportingPerson page is returned.
     * else viewEmployeePage is returned.
     * 
     */
    @RequestMapping(value = CREATE_EMPLOYEE, method = RequestMethod.POST)
    private String addEmployee(@RequestParam(SELECTED_ROLE) int roleId, 
            @ModelAttribute EmployeeInfo employeeInfo, Model model) 
            throws IOException {
        employeeInfo = employeeService.createEmployee(employeeInfo, roleId);
        if ((employeeInfo.getRole()).equals("ROLE_MANAGER") 
                || (employeeInfo.getRole()).equals("ROLE_ADMIN")) {
            model.addAttribute(Constant.EMPLOYEE_INFO, employeeInfo);
            return VIEW_EMPLOYEE_PAGE;
        } else {
            List<EmployeeInfo> reportingPersons = 
                    employeeService.getReportingPerson(employeeInfo.getRole());
            model.addAttribute(Constant.EMPLOYEE_INFO, employeeInfo);
            model.addAttribute(REPORTING_PERSONS, reportingPersons);
            return ASSING_REPORTING_PERSON_PAGE;
        }
        
    } 
    
    /**
     * Get the employee id and get the employee reporter person id
     * 
     *
     * @param employeeid - it carry the employee id
     * @param reportingPersonId - it carry the reporting person id
     * @return VIEW_EMPLOYEE_PAGE - jsp page to be redirected.
     */
    @RequestMapping(value = "assign-reporting-person"
            , method = RequestMethod.POST)
    private String assignReportingPerson(
            @RequestParam("id") int employeeId,
            @RequestParam("reportingPersonId") int reportingPersonId,
            Model model) {
        EmployeeInfo employeeInfo = employeeService.addReportingPersonForEmployee(
                employeeId, reportingPersonId);
        model.addAttribute(Constant.EMPLOYEE_INFO, employeeInfo);
        return VIEW_EMPLOYEE_PAGE;
    }
       
    /** 
     * Gets the employee id to be edited and returns model 
     * with that employee details.
     *
     * @param id - Id of the employee to be edited
     * @param model - Used to adding attributes to the model.
     * @return editEmployee - Jsp page to be directed.
     */
    @RequestMapping(value = EDIT_EMPLOYEE, method = RequestMethod.GET)
    private String editEmployee(
            @RequestParam(Constant.ID) int id, Model model) {
        EmployeeInfo employeeInfo = employeeService.fetchEmployeeInfo(id);
        model.addAttribute(Constant.EMPLOYEE_INFO,employeeInfo);
        return EDIT_EMPLOYEE_PAGE;
    }

    /**
     * Gets the updated information from user as object and updates.
     *
     * @param employeeInfo - Object with inputs given by user.
     * @param model - brings object with inputs.
     * @return viewemployee - Jsp page to be directed.
     */
    @RequestMapping(value = UPDATE_EMPLOYEE, method = RequestMethod.POST)
    private String updateEmployee(@ModelAttribute EmployeeInfo employeeInfo
            , Model model)
            throws IOException {
        employeeInfo = employeeService.updateEmployee(employeeInfo);
        model.addAttribute(Constant.EMPLOYEE_INFO,employeeInfo);
        return VIEW_EMPLOYEE_PAGE;
    }
    
    /** 
     * Gets id, fethches object and gives object to viewemployee page
     *
     * @param id - Id of the employee to be viewed
     * @param model - Used to adding attributes to the model.
     * @return viewemployee - Jsp page to be directed.
     */
    @RequestMapping(value = VIEW_EMPLOYEE)
    private String displayEmployee(
            @RequestParam(Constant.ID) int id, Model model) {
        EmployeeInfo employeeInfo = employeeService.fetchEmployeeInfo(id);
        model.addAttribute(Constant.EMPLOYEE_INFO,employeeInfo);
        return VIEW_EMPLOYEE_PAGE;
    }

    /** 
     * Fetches number of employees fits to first page.
     *
     * @param model - Used to adding attributes to the model. 
     * @return viewallEmployees - Jsp page to be directed.
     */
    @RequestMapping(value = VIEW_EMPLOYEES, method = RequestMethod.GET)
    private String displayAllEmployees(Model model) { 
        EmployeePaginationInfo employeePaginationInfo =
                employeeService.fetchEmployees(0);
        model.addAttribute(Constant.EMPLOYEE_PAGINATION_INFO
                , employeePaginationInfo);
        model.addAttribute(Constant.CURRENT_PAGE, 1);
        return VIEW_ALL_EMPLOYEE_PAGE;
    }
    
    /**
     * Soft delete of employee is done by getting id of empployee.
     *
     * @param id - Id of the employee to be deleted
     * @param model - Used to adding attributes to the model.
     * @return viewEmployees - Jsp page to be directed. 
     */
    @RequestMapping(value = DELETE_EMPLOYEE, method = RequestMethod.GET)
    private String deleteEmployee(
            @RequestParam(Constant.ID) int id, Model model)  {
        employeeService.deleteEmployee(id);
        return REDIRECT_VIEW_EMPLOYEE_PAGE;
    } 
    
    /**
     * Gets page number and fetches employees as per pages and returns to page.
     *
     * @param request - request containing values from localhost
     * @param response - response for the localhost request
     */
    @RequestMapping(value = VIEW_EMPLOYEES_PAGE_WISE
            , method = RequestMethod.GET)
    private void viewEmployees(
            HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        int page = Integer.parseInt(request.getParameter(Constant.PAGE));
        List<EmployeeInfo> employees = employeeService.fetchEmployeesByPage(page);
        JSONArray array = new JSONArray();
        for (EmployeeInfo employeeInfo : employees) { 
            JSONObject jsonEmployee = new JSONObject();
            jsonEmployee.put(Constant.ID, employeeInfo.getId());
            jsonEmployee.put(Constant.NAME, employeeInfo.getName());
            jsonEmployee.put("reportingPerson", employeeInfo.getReportingPerson());
            jsonEmployee.put("role", employeeInfo.getRole());
            array.put(jsonEmployee);
        }
        response.setContentType(APP_JSON);
        response.getWriter().write(array.toString());
    }
    
    /**
     * Profile view for the logged in person. We get id from session
     * to fetch and view own profile.
     * 
     * @return model - used to add objects and attributes.
     */
    @RequestMapping(value="profile")
    private String displayProfile(Model model)  {
        EmployeeInfo employee = employeeService.getEmployeeProfile();
        model.addAttribute("employee",employee);
        return "profile";
    } 

   @RequestMapping(value="display-subordinates")
    private ModelAndView displaySubordinates()  {
        ModelAndView model = new ModelAndView("displaySubordinates");
        List<Employee> subordinates = employeeService.fetchSubordinates();
        model.addObject("subordinates",subordinates);
        return model;
    }
            
}
