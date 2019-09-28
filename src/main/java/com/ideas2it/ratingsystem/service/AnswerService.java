package com.ideas2it.ratingsystem.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ideas2it.ratingsystem.model.Answer;
import com.ideas2it.ratingsystem.model.Form;
import com.ideas2it.ratingsystem.model.Employee;
/**
 * It perform the some logical operation for created date 
 * and perform add ,delete,update,get all information for role
 */
public interface AnswerService {
    
    /**
     * It adds all the answers and comments given by an employee. The answers
     * are obtained in a map in which each response is mapped to a question
     * Id.
     * 
     * @param formId - The form Id of the form filled by employee
     * @param responses - The map containing response-question Id pairs
     */ 
    void saveResponses(int formId, Map<String,String[]> responses);
    
    /**
     * It differentiates if the response submitted by the employee
     * is one of given option or the comment given by him/her
     * and then adds them to a particular answer
     *
     * @param answer - The answer to which the response and comments are to be added
     * @param questionId - The question Id of question in context
     * @param keyValues - The map containing the question Id - response map
     */  
    void setResponseToAnswer(Answer answer,String questionId, Map<String, String> keyValues);
    
    /**
     * It returns responses given by an employee for a particular form
     *
     * @param formId - The form for which the responses are required
     */
    List<Answer> getEmployeeResponseByForm(int formId);
    
     /**
     * It returns a list of all answers submitted by the employee currently
     * logged in
     * 
     */
    Set<Form> getEmployeeResponses();
    
    /**
     * It returns a list of all answers submitted by a particular employee based on
     * employee Id
     * 
     * @param employeeId - The employee Id of the employee whoose responses are required
     */
    List<Answer> getEmployeeResponsesByEmployeeId(int employeeId);
    
    /**
     * It returns a list of answers submitted by a list of employees based on
     * their employee Ids
     *
     * @param employeeIds - The list of employee Ids
     */
    List<Answer> getResponsesByEmployeeIds(List<Integer> employeeIds);
    
    /**
     * It returns a list of all answers submitted by a particular employee based on
     * employee Id
     * 
     * @param employeeId - The employee Id of the employee whoose responses are required
     * @param formId - The form for which the responses are required
     */
    List<Answer> getEmployeeResponseByFormIdAndEmployeeId(int employeeId, int formId);

    List<Answer> getAnswersByFormId(Form form);


    public List<Answer> getEmployeeResponseByFormAndEmployee(Employee employee, Form form);

    public Set<Form> getEmployeeForms(int employeeId);
}
