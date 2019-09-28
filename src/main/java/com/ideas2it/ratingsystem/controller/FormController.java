package com.ideas2it.ratingsystem.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException; 
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.servlet.ModelAndView;

import com.ideas2it.ratingsystem.model.Question;
import com.ideas2it.ratingsystem.model.Answer;
import com.ideas2it.ratingsystem.model.Form;
import com.ideas2it.ratingsystem.model.Employee;
import com.ideas2it.ratingsystem.model.Role;
import com.ideas2it.ratingsystem.service.FormService;
import com.ideas2it.ratingsystem.service.RoleService;


/**
 * Use to get information from users and Add into database
 * Delete tha information based on user given id
 * It update the existing values into new values
 * Get particular form information based on existing id
 * Search existing entry 
 */
@Controller  
public class FormController {
    
    @Autowired    
    private FormService formService;   
    
    @Autowired
    private RoleService roleService;     
        
    private static final String ADD_QUESTIONS_TO_FORM = "addQuestionsToForm";
    private static final String SET_FORM = "form";
    private static final String SAVE_FORM = "add-form";
    private static final String SELECTED_ROLES = "selectedRoles";
    private static final String FORM_ID_PARAMETER = "formId";


    /**
     * Get the form name and description for form
     * TODO Error page
     *
     * @param id - It carry the new form information
     */
   @RequestMapping(value = "new-form")      
    public ModelAndView createForm() {
        ModelAndView model = new ModelAndView("createForm");
        model.addObject("form", new Form());
        model.addObject("roles",roleService.getAllRoleInformation());
        return model;
    }

    /**
     * Get the form name and description for form
     * TODO Error page
     *
     * @param id - It carry the new form information
     */
   @RequestMapping(value = SAVE_FORM)      
    public ModelAndView addForm(@ModelAttribute(SET_FORM) Form form,
        @RequestParam(required = false, name = SELECTED_ROLES) String[] roleIds) { 
        ModelAndView model = new ModelAndView();
        formService.addForm(form, roleIds);
        model.addObject(SET_FORM, form);
        model.setViewName(ADD_QUESTIONS_TO_FORM);
        return model;
    }
/**
     * it display display form for form
     *
     * @param id - it carry the form id
     * @reyurn model - redirect the form display page include form object
     */
   @RequestMapping(value = "display-form")      
    public ModelAndView displayForm(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("displayForm");
        try {
            int formId = Integer.parseInt(request.getParameter("formId"));
            System.out.println(formService.getFormStats(formId));
            Form form = formService.getFormById(formId);
            model.addObject("form",form);
        } catch(Exception e) {
            //TODO
            //return error;
        }
        return model;
    }


    /**
     * It allows the admin to edit all details of a form except the questions
     */
    public ModelAndView editFormDetails() {
        ModelAndView model = new ModelAndView("Edit-form-details");
        return model;
    }

    /**
     * It allows the admin to edit the questions inside a form that is being displayed
     */
    public ModelAndView editFormQuestions() {
        ModelAndView model = new ModelAndView("Edit-form-questions");
        return model;
    }

    /**
     * It updates the form details as provided by the admin
     *
     * @param form - The updated form received from jsp 
     * @param request - The request object containig role Ids
     */
    public ModelAndView updateFormDetails(Form form, HttpServletRequest request) {
        ModelAndView model = new ModelAndView("Display-edit-page");
        try {
            String roleIds[] = request.getParameterValues("roleId");
            formService.updateFormDetails(form,roleIds);
        } catch(Exception e) {
            //TODO
            //return error;
        }
        return model;
    }

    /**
     * It allows the admin to edit the questions inside a form that is being displayed
     *
     * @param form - The updated form received from jsp 
     */
    public ModelAndView updateFormQuestions(Form form) {
        ModelAndView model = new ModelAndView("Display-edit-page");
        try {
            formService.updateFormQuestions(form);
        } catch(Exception e) {
            //TODO
            //return error;
        }
        return model;
    }

    /**
     * It allows the admin to edit the questions inside a form that is being displayed
     *
     * @param form - The updated form received from jsp 
     */
    public ModelAndView createFormFromExistingForm(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("Display-form-page");
        try {
            int formId = Integer.parseInt(request.getParameter(FORM_ID_PARAMETER));
            //Form form = formService.createFormFromExistingForm(formId);
            Form form = new Form();
            List<Role> roles = roleService.getAllRoleInformation();
            model.addObject("oldFormId", formId);
            model.addObject("roles", roles);
            model.addObject("form",form);
        } catch(Exception e) {
            //TODO
            //return error;
        }
        return model;
    }

    /**
     * It allows the admin to edit the questions inside a form that is being displayed
     *
     * @param form - The updated form received from jsp 
     */
    public ModelAndView saveNewForm(Form form, HttpServletRequest request) {
        ModelAndView model = new ModelAndView("Display-form-page");
        try {
            int oldFormId = Integer.parseInt(request.getParameter("oldFormId"));
            String roleIds[] = request.getParameterValues("roleId");
            Form newForm = formService.createFormFromExistingForm(form,oldFormId,roleIds);
            model.addObject(newForm);
        } catch(Exception e) {
            //TODO
            //return error;
        }
        return model;
    }

    @RequestMapping(value = "publish-form") 
    public ModelAndView publishForm(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("adminHome");
        try{
            int formId = Integer.parseInt(request.getParameter(FORM_ID_PARAMETER));
            formService.publishForm(formId);
            return model;
        } catch(Exception e) {
            //TODO
            //return error;
        }
        return model;
    }
    
    public ModelAndView createResponseForm(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("response-page");
        try{
            int formId = Integer.parseInt(request.getParameter(FORM_ID_PARAMETER));
            model.addObject("form",formService.getFormById(formId));
            model.addObject("formId",formId);
            return model;
        } catch(Exception e) {
            //TODO
            //return error;
        }
        return model;
    }
    @RequestMapping(value = "form-stats") 
    public ModelAndView showFormStats(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("formStats");
        try {
            int formId = Integer.parseInt(request.getParameter(FORM_ID_PARAMETER));
            Form form = formService.getFormById(formId);
            Set<Question> questions = form.getQuestions();
            Map<Integer,Map<Integer,Integer>> formStats = formService.getFormStats(formId);
            model.addObject("formStats",formStats);
            model.addObject("questions",questions);
        } catch(Exception e) {
            //TODO
            //return error;
        }
        return model;
    }

    @RequestMapping(value = "display-all-forms") 
    public ModelAndView showForms() {
        ModelAndView model = new ModelAndView("displayAllForms");
        try {
            List<Form> forms = formService.getAllForms();
            model.addObject("forms",forms);
        } catch(Exception e) {
                //TODO
                //return error;
        }
        return model;
    }

    @RequestMapping(value = "get-employee-forms") 
    public ModelAndView showEmployeeForms(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("showEmployeeForms");
        try {
            int employeeId = Integer.parseInt(request.getParameter("employeeId"));
            Set<Form> forms = formService.getFormsByEmployeeId(employeeId);
            model.addObject("forms",forms);
            model.addObject("employeeId",employeeId);
        } catch(Exception e) {
                //TODO
                //return error;
        }
        return model;
    }
}

