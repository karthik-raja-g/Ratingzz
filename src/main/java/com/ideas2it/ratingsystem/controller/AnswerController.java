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
import org.springframework.web.servlet.ModelAndView;

import com.ideas2it.ratingsystem.model.Question;
import com.ideas2it.ratingsystem.model.Answer;
import com.ideas2it.ratingsystem.model.Employee;
import com.ideas2it.ratingsystem.model.Form;
import com.ideas2it.ratingsystem.service.AnswerService;
import com.ideas2it.ratingsystem.service.FormService;

/**
 *<p>
 * The responses submitted by each employee is stored. Each question
 * has an answer from an employee. The controller maps the correct 
 * employee-form-answer combination
 *</p>
 */
@Controller
public class AnswerController {

    private static final String ADD_ANSWER_URL = "/add-answers"; 
    private static final String FORM_ID_PARAMETER = "formId";
    private static final String EMPLOYEE_ID_PARAMETER = "employeeId";
    private static final String RESPONSE_PAGE_JSP = "responsePage";
    private static final String RESPONSES_ATTRIBUTE = "responses";
    private static final String GET_EMPLOYEE_ANSWERS_URL = "/get-answers-by-employee";
    private static final String GET_ANSWERS_BY_FORM_URL = "/get-answers-by-formId";
    private static final String GET_ANSWERS_BY_EMPLOYEE_ID_URL  = "/get-answers-by-employeeId";
    private static final String GET_ANSWERS_BY_EMPLOYEE_ID_AND_FORM_ID_URL  = "/get-answers-by-employeeId-formId";

    @Autowired
    private AnswerService answerService ;

    @Autowired
    private FormService formService ;

    /**
     * It gets all the responses submitted by a employee in a particular form
     *
     * @param request - The request object containing all required information
     * @return model - Used to redirect the employee to his/her homepage
     */
    @RequestMapping(value = ADD_ANSWER_URL)
    public ModelAndView addResponses(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("adminHome");
        try {
            int formId = Integer.parseInt(request.getParameter("formId"));
            System.out.println(formId+"&&&&&&&&&&&&&&&&&&&&\n");
            System.out.println("\n"+formService.getFormStats(formId)+"\n");
            Map responseDetails = request.getParameterMap();
            System.out.println(responseDetails);
            answerService.saveResponses(formId,responseDetails);
        } catch(Exception e) {
            //TODO
            //return error;
        }
        return model;
    }

    /**
     * It gets all the answers submitted by the logged in employee
     *
     * @return model - object containig the responses submitted the employee
     */
    @RequestMapping(value = "get-answers-by-employee")
    public ModelAndView getEmployeeForms() {
        ModelAndView model = new ModelAndView("showEmployeeForms");
        try {
            Set<Form> forms = answerService.getEmployeeResponses();
            model.addObject("forms",forms);
            return model;
        } catch(Exception e) {
            //TODO
            //return error;
        }
        return model;
    }

    /**
     * It gets all the answers submitted by the logged in employee
     *
     * @return model - object containig the responses submitted the employee
     */
    @RequestMapping(value = "get-response-by-form-employee")
    public ModelAndView getEmployeeResponses(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("displayEmployeeResponse");
        try{
            int formId = Integer.parseInt(request.getParameter("formId"));
            List<Answer> answers = answerService.getEmployeeResponseByForm(formId);
            Form form = answers.get(0).getForm();
            model.addObject("answers", answers);
            model.addObject("form", form);
        } catch(Exception e) {
            //TODO
            //return error;
        }
        return model;
    }

    /**
     * It gets all the answers submitted by the logged in employee
     *
     * @return model - object containig the responses submitted the employee
     */
    @RequestMapping(value = "get-response-by-form-employeeId")
    public ModelAndView getEmployeeResponsesById(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("displayEmployeeResponse");
        try{
            int formId = Integer.parseInt(request.getParameter("formId"));
            int employeeId = Integer.parseInt(request.getParameter("employeeId"));
            List<Answer> answers = answerService.getEmployeeResponseByFormIdAndEmployeeId(employeeId,formId);
            Form form = answers.get(0).getForm();
            model.addObject("answers", answers);
            model.addObject("form", form);
        } catch(Exception e) {
            //TODO
            //return error;
        }
        return model;
    }


    /**
     * It retirves the response of a particular form filled by the employee
     *
     * @param request - The request object containing form Id
     * @return model - the object with answers subitted by the logged in employee for 
     *                 a particular form
     */
    @RequestMapping(value = GET_ANSWERS_BY_FORM_URL)
    public ModelAndView getEmployeeResponseByForm(HttpServletRequest request) {
        int formId = Integer.parseInt(request.getParameter(FORM_ID_PARAMETER));
        ModelAndView model = new ModelAndView(RESPONSE_PAGE_JSP);
        try {
            List<Answer> responses = answerService.getEmployeeResponseByForm(formId);
            model.addObject(RESPONSES_ATTRIBUTE, responses);
            return model;
        } catch(Exception e) {
            //TODO
            //return error;
        }
        return model;
    }

    /**
     * It returns all the reponses given by a particular emplyee based on employee Id
     *
     * @param request - The request object containing the employee Id
     * @return model - object containing answers submitted by a employee  
     */
    @RequestMapping(value = GET_ANSWERS_BY_EMPLOYEE_ID_URL)
    public ModelAndView getEmployeeResponsesByEmployeeId(HttpServletRequest request) {
        int employeeId = Integer.parseInt(request.getParameter(EMPLOYEE_ID_PARAMETER));
        ModelAndView model = new ModelAndView(RESPONSE_PAGE_JSP);
        try {

            List<Answer> responses = answerService.getEmployeeResponsesByEmployeeId(employeeId);
            model.addObject(RESPONSES_ATTRIBUTE, responses);
            return model;
        } catch(Exception e) {
            //TODO
            //return error;
        }
        return model;
    }

    @RequestMapping(value = "/*get-employee-to-response-page*")
    public ModelAndView getEmplResponse(HttpServletRequest request) {
            ModelAndView model = new ModelAndView("employeeResponse");
        try {
        int formId = Integer.parseInt(request.getQueryString());
        formService.checkEmployeeForResponse(formId);
        Form form = formService.getFormById(formId);
        model.addObject("form",form);
        } catch(Exception ex) {
            ModelAndView error = new ModelAndView("Error");
            error.addObject("error",ex);
            return error;
        }
        return model;
    }
    

    /**
     * It returns all the reponses given by a particular emplyee based on employee Id
     *
     * @param request - The request object containing the employee Id
     * @return model - The object with responses of a particular employee for a form 
     */
    @RequestMapping(value = GET_ANSWERS_BY_EMPLOYEE_ID_AND_FORM_ID_URL )
    public ModelAndView getEmployeeResponseByFormIdAndEmployeeId(HttpServletRequest request) {
        int employeeId = Integer.parseInt(request.getParameter(EMPLOYEE_ID_PARAMETER));
        int formId = Integer.parseInt(request.getParameter(FORM_ID_PARAMETER));
        ModelAndView model = new ModelAndView(RESPONSE_PAGE_JSP);
        try {
            List<Answer> responses = answerService.getEmployeeResponseByFormIdAndEmployeeId(employeeId,formId);
            model.addObject(RESPONSES_ATTRIBUTE, responses);
            return model;
        } catch(Exception e) {
            //TODO
            //return error;
        }
        return model;
    }
            
}
   

        


