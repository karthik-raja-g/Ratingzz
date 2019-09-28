package com.ideas2it.ratingsystem.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException; 
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;    
import org.springframework.stereotype.Service;

import com.ideas2it.ratingsystem.dao.AnswerDao;
import com.ideas2it.ratingsystem.model.Question;
import com.ideas2it.ratingsystem.model.Answer;
import com.ideas2it.ratingsystem.model.Form;

import com.ideas2it.ratingsystem.model.Employee;
import com.ideas2it.ratingsystem.service.AnswerService;
import com.ideas2it.ratingsystem.service.EmployeeService;
import com.ideas2it.ratingsystem.service.QuestionService;
import com.ideas2it.ratingsystem.service.FormService;
import com.ideas2it.ratingsystem.util.DateUtil;
import com.ideas2it.ratingsystem.util.SessionEmployeeProvider;

/**
 *<p>
 * The Answer service class implementation does all the business logic
 * for the all responses submitted by the emloyees
 *
 * @author karthik Created on 7 September 2019
 */
@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private EmployeeService employeeService ;

    @Autowired
    private QuestionService questionService ;

    @Autowired
    private FormService formService ;

    @Autowired
    private AnswerDao answerDao ;

    private static final String RADIO_BUTTON_FIELD = "radio";
    private static final String COMMENTS_FIELD = "comments";
    private static final String FORM_FIELD = "formId";
    private static final String PARAMETER_NAME_SPLITTER = "_";
    private static final String NULL_STRING = "";

    /**
     * It adds all the answers and comments given by an employee. The answers
     * are obtained in a map in which each response is mapped to a question
     * Id.
     * 
     * @param formId - The form Id of the form filled by employee
     * @param responses - The map containing response-question Id pairs
     */ 
    public void saveResponses(int formId, Map responses) {
        Iterator iter = responses.keySet().iterator();
        Map<String,String> keyValues = new HashMap<String,String>();
        List<String> keys = new ArrayList<String>();
        while(iter.hasNext()) {
            String key = (String)iter.next();
            keys.add(key);
            keyValues.put(key,(((String[]) responses.get(key))[0]));
        }
        keys.remove("formId");
        keyValues.remove("formId");
        System.out.println(keyValues);
        System.out.println(keys);
        int skipIndex = -1 ;
        for(String key : keys) {
            System.out.println(key);
            String questionId = key.split("_")[0];
            String respType = key.split("_")[1];
            if(respType.equals("radio") && skipIndex != keys.indexOf(key)) {
                System.out.println(key);
                int qId = Integer.parseInt(questionId);
                int choice = Integer.parseInt(keyValues.get(key));
                String com = questionId+"_comments";
                String comments = keyValues.get(com);
                skipIndex = keys.indexOf(com);
                Answer answer = new Answer();
                answer.setAnswer(choice);
                answer.setComments(comments);
                answer.setQuestion(questionService.getQuestionById(qId));
                answer.setForm(formService.getFormById(formId));
                answer.setResponder(SessionEmployeeProvider.getSessionEmployee());
                answer.setCreatedDate(DateUtil.getDate());
                answer.setModifiedDate(DateUtil.getDate());
                answerDao.insertAnswer(answer);
                System.out.println(keys);
            }
            if(respType.equals("comments") && skipIndex != keys.indexOf(key)) {
                System.out.println(key);
                int qId = Integer.parseInt(questionId);
                String comments = keyValues.get(key);
                String rad = questionId+"_radio";
                int choice = Integer.parseInt(keyValues.get(rad));
                skipIndex = keys.indexOf(rad);
                Answer answer = new Answer();
                answer.setAnswer(choice);
                answer.setComments(comments);
                answer.setQuestion(questionService.getQuestionById(qId));
                answer.setForm(formService.getFormById(formId));
                answer.setResponder(SessionEmployeeProvider.getSessionEmployee());
                answer.setCreatedDate(DateUtil.getDate());
                answer.setModifiedDate(DateUtil.getDate());
                answerDao.insertAnswer(answer);
                System.out.println(keys);
            }
        }
    }

    /**
     * It differentiates if the response submitted by the employee
     * is one of given option or the comment given by him/her
     * and then adds them to a particular answer
     *
     * @param answer - The answer to which the response and comments are to be added
     * @param questionId - The question Id of question in context
     * @param keyValues - The map containing the question Id - response map
     */
    public void setResponseToAnswer(Answer answer,String questionId, Map<String, String> keyValues) {
        String choice = NULL_STRING;
        String comments= NULL_STRING;
        for(String response : keyValues.keySet()) {
            if(response.contains(RADIO_BUTTON_FIELD) && response.contains(questionId)) {
                choice = keyValues.get(response); 
            }
            if(response.contains(COMMENTS_FIELD) && response.contains(questionId)) {
                comments = keyValues.get(response); 
            }
            
        }
        answer.setAnswer(Integer.parseInt(choice));
        answer.setComments(comments);
    }


    /**
     * It returns responses given by an employee for a particular form
     *
     * @param formId - The form for which the responses are required
     * @return - a list of answers of the form subitted by employee
     */
    public List<Answer> getEmployeeResponseByForm(int formId) {
        Employee employee = SessionEmployeeProvider.getSessionEmployee();
        Form form = formService.getFormById(formId);
        return answerDao.fetchAnswersByFormsFilledByEmployee(employee,form);
    }

    /**
     * It returns a list of all answers submitted by the employee currently
     * logged in
     * 
     * @return - a list of all answers submitted by the employee logged in
     */
    public Set<Form> getEmployeeResponses() {
        Employee employee = SessionEmployeeProvider.getSessionEmployee();
        Set<Form> forms = new HashSet<Form>();
        for(Answer answer : answerDao.fetchAnswersByEmployee(employee)) {
            System.out.println(answer); 
            forms.add(answer.getForm());
        }
        System.out.println(forms);
        return forms;
    }

    /**
     * It returns a list of all answers submitted by the employee currently
     * logged in
     * 
     * @return - a list of all answers submitted by the employee logged in
     */
    public Set<Form> getEmployeeForms(int employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        Set<Form> forms = new HashSet<Form>();
        for(Answer answer : answerDao.fetchAnswersByEmployee(employee)) {
            System.out.println(answer); 
            forms.add(answer.getForm());
        }
        System.out.println(forms);
        return forms;
    }

    /**
     * It returns a list of all answers submitted by a particular employee based on
     * employee Id
     * 
     * @param employeeId - The employee Id of the employee whoose responses are required
     * @return - a list of all answers submitted by a particular employee
     */
    public List<Answer> getEmployeeResponsesByEmployeeId(int employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        return answerDao.fetchAnswersByEmployee(employee);
    }

    /**
     * It returns a list of all answers submitted by a particular employee based on
     * employee Id
     * 
     * @param employeeId - The employee Id of the employee whoose responses are required
     * @param formId - The form for which the responses are required
     * @return - a list of answers submitted by a particular employee for particular form
     */
    public List<Answer> getEmployeeResponseByFormIdAndEmployeeId(int employeeId, int formId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        Form form = formService.getFormById(formId);
        return answerDao.fetchAnswersByFormsFilledByEmployee(employee,form);
    }

    /**
     * It returns a list of answers submitted by a list of employees based on
     * their employee Ids
     *
     * @param employeeIds - The list of employee Ids
     * @return - a list of all answers submitted by the list of employees
     */
    public List<Answer> getResponsesByEmployeeIds(List<Integer> employeeIds) {
        return answerDao.retriveAnswersByEmployeesIds(employeeIds);
    }

    public List<Answer> getAnswersByFormId(Form form) {
        return answerDao.retriveAnswersByForm(form);
    }

    /**
     * It returns a list of all answers submitted by a particular employee based on
     * employee Id
     * 
     * @param employeeId - The employee Id of the employee whoose responses are required
     * @param formId - The form for which the responses are required
     * @return - a list of answers submitted by a particular employee for particular form
     */
    public List<Answer> getEmployeeResponseByFormAndEmployee(Employee employee, Form form) {
        return answerDao.fetchAnswersByFormsFilledByEmployee(employee,form);
    }
    
}
        

