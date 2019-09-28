package com.ideas2it.ratingsystem.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException; 
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;    
import org.springframework.stereotype.Service;

import com.ideas2it.ratingsystem.dao.FormDao;
import com.ideas2it.ratingsystem.model.Question;
import com.ideas2it.ratingsystem.model.Answer;
import com.ideas2it.ratingsystem.model.Employee;
import com.ideas2it.ratingsystem.model.Form;
import com.ideas2it.ratingsystem.model.Role;
import com.ideas2it.ratingsystem.service.FormService;
import com.ideas2it.ratingsystem.service.EmployeeService;
import com.ideas2it.ratingsystem.service.impl.MailServiceImpl;
import com.ideas2it.ratingsystem.service.QuestionService;
import com.ideas2it.ratingsystem.service.RoleService;
import com.ideas2it.ratingsystem.service.AnswerService;
import com.ideas2it.ratingsystem.util.SessionEmployeeProvider;
import com.ideas2it.ratingsystem.util.DateUtil;
import com.ideas2it.ratingsystem.util.AppException;

/**
 *<p>
 * Form business logics are performed here. Mailing list for the forms
 * of employees are obtained here and sent to mail service 
 *</p>
 *
 * @author karthik created on 9 September 2019
 */
@Service
public class FormServiceImpl implements FormService {

    @Autowired
    private FormDao formDao ;

    @Autowired
    private EmployeeService employeeService ;

    @Autowired
    private QuestionService questionService ;

    @Autowired
    private RoleService roleService ;

    @Autowired
    private AnswerService answerService ;

    @Autowired
    private MailServiceImpl mailService ;

    
    /**
     * {@inheritdoc}
     */
    public Form addForm(Form form, String[] ids) {
        List<Role> roles = new ArrayList<Role>();
        form.setStatus(Boolean.TRUE);
        form.setCreatedDate(DateUtil.getDate());
        form.setCreator(SessionEmployeeProvider.getSessionEmployee());
        roles = roleService.getRolesByIds(ids);
        Set<Role> requireRoles = new HashSet<Role>(roles); 
        form.setRoles(requireRoles);
        return formDao.insertForm(form);
    }
    
    /**
     * {@inheritdoc}
     */
    public Form getFormById(int id) {
        return formDao.getFormById(id);
    }

    /**
     * It saves updated details of the form into database
     *
     * @param form - The updated form object
     * @param roleIds - The role Ids to which the form has to be published
     */
    public void updateFormDetails(Form form, String[] roleIds) {
        List<Role> roles = roleService.getRolesByIds(roleIds);
        Set<Role> requireRoles = new HashSet<Role>(roles);
        Form existingForm = getFormById(form.getId());
        existingForm.setTitle(form.getTitle());
        existingForm.setDescription(form.getDescription());
        existingForm.setRoles(requireRoles);
        existingForm.setStatus(form.getStatus());
        existingForm.setModifiedDate(DateUtil.getDate());
        formDao.updateForm(existingForm);
    }

    /**
     * It saves updated questions of the form into database
     *
     * @param form - The updated form object
     * @param roleIds - The role Ids to which the form has to be published
     */
    public void updateFormQuestions(Form form) {
        Form existingForm = getFormById(form.getId());
        Set<Question> newQuestions = new HashSet<Question>();
        for(Question question : form.getQuestions())
            newQuestions.add(question);
        existingForm.setQuestions(newQuestions);
        formDao.updateForm(existingForm);
    }

    /**
     * {inheritdoc}
     */
    public Form createFormFromExistingForm(Form form,int oldFormId,String roleIds[]) {
        Form freshForm = formDao.insertForm(form);
        Form oldForm = formDao.getFormById(oldFormId);
        freshForm.setQuestions(questionService.createQuestionsForNewForm(oldForm,freshForm));
        return formDao.updateForm(freshForm);
    }
    
    /**
     * {inheritdoc}
     */
    public void updateForm(Form form, Question question) {
        Set<Question> newQuestions = form.getQuestions();
        newQuestions.add(question);
        form.setQuestions(newQuestions);
        formDao.updateForm(form);
    }
    
    /**
     * It publishes a form to number of employees. The condition is
     * matched by the roles specified in the form and employee role
     *
     * @param formId - The form Id of form to be published
     */
    public void publishForm(int formId) {
        Form form = getFormById(formId);
        List<Employee> employees = new ArrayList<Employee>();
        for(Role role : form.getRoles()) {
            System.out.println(role+"@@##$$%%");
            for(Employee employee : employeeService.fetchEmployeesByRole(role)) {
                System.out.println(employee+"!!!!!!!!!!!!!!!!!!!!!!!!");
                employees.add(employee);        
            }
        }
        for (Employee employee : employees) {
            mailService.setFormId(formId);
            mailService.sendEmail(employee);
            System.out.println("$$$$$$$$$$$$$$$$$$$serv 156");
        }
    }

    public List<Form> getFormsByIds(List<Integer> formIds) {
        return formDao.getFormsByIds(formIds);
    }

    /**
     * It validates if an employee has an invitaion to fill a particular
     * form, based on the roles specified by the form
     *
     * @param formId - THe form Id of form needed for response
     * @return form - The form for response
     * @exception - If the employee is not authorized to fill the form
     */
    public Form getFormForResponse(int formId) {
        Employee employee = SessionEmployeeProvider.getSessionEmployee();
        Form form = getFormById(formId);
        if(!isAuthorizedToFillForm(employee,form)) {
            //TODO
            // throw new exception
            return null;
        }
        return form;
    }

    /**
     * It checks if logged in employee is authorized to fill a form
     * 
     * @param form - The form for to submit response
     * @param employee - The employee in context of verification
     * @return Boolean - True or false based on authorization
     */
    private Boolean isAuthorizedToFillForm(Employee employee,Form form) {
        for (Role role : form.getRoles()) {
            if(role.getName().equals(employee.getRole().getName()))
                return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * It returns a map of questions and the overall rating count it each question
     * got from the responders.
     *
     * @param formId - The formId of form for which the stats are required
     * @return formStats - The map containig the stats about each question of the form
     */
    public Map<Integer,Map<Integer,Integer>> getFormStats(int formId) {
        Form form = getFormById(formId);
        Set<Question> questions = form.getQuestions();
        List<Answer> answers = answerService.getAnswersByFormId(form);
        Map<Integer,Map<Integer,Integer>> formStats = new HashMap<Integer,Map<Integer,Integer>>();
        for(Question question : questions) {
            Map<Integer,Integer> questionStat = new HashMap<Integer,Integer>();
            int oneStarCount = 0;
            int twoStarCount = 0;
            int threeStarCount = 0;
            int fourStarCount = 0;
            int fiveStarCount = 0;
            for(Answer answer : answers) {
                if(1 == answer.getAnswer())
                    oneStarCount +=1;
                if(2 == answer.getAnswer())
                    twoStarCount +=1;
                if(3 == answer.getAnswer())
                    threeStarCount +=1;
                if(4 == answer.getAnswer())
                    fourStarCount +=1;
                if(5 == answer.getAnswer())
                    fiveStarCount +=1;
            }
            questionStat.put(1,oneStarCount);
            questionStat.put(2,twoStarCount);
            questionStat.put(3,threeStarCount);
            questionStat.put(4,fourStarCount);
            questionStat.put(5,fiveStarCount);
            formStats.put(question.getId(),questionStat);
        }
        return formStats;
    } 

    public void checkEmployeeForResponse(int formId) throws AppException {
        Form form = getFormById(formId);
        Employee employee = SessionEmployeeProvider.getSessionEmployee();
        List<Answer> answers = answerService.getEmployeeResponseByFormAndEmployee(employee,form);
        if(0!=answers.size())
            throw new AppException("You have submitted this form already. You can go to home page and check your response");
        }

    public List<Form> getAllForms() {
        return formDao.retriveAllForms() ;
    }

    public Set<Form> getFormsByEmployeeId(int employeeId){
        return answerService.getEmployeeForms(employeeId);
    }
}
