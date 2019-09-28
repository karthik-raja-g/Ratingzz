package com.ideas2it.ratingsystem.dao;

import java.util.List;

import com.ideas2it.ratingsystem.model.Answer;
import com.ideas2it.ratingsystem.model.Employee;
import com.ideas2it.ratingsystem.model.Form;
    
/**
 * It perform insert,delete,update,display operation
 * It get the information from database
 */
public interface AnswerDao {

    /**
     * It saves an answer into database
     *
     * @param answer - The answer object to be saved
     */
    void insertAnswer(Answer answer);

    /**
     * It returns a answer based on user Id
     *
     * @param         id              The answer Id
     */
    Answer retriveAnswerById(int id);

    /**
     * It returns list of all answers submitted by a particular employee
     * 
     * @param employee - The employee whoose respones are required
     */
    List<Answer> fetchAnswersByEmployee(Employee employee);

    /**
     * It returns list of all answers submitted by a particular employee
     * 
     * @param employee - The employee whoose respones are required
     */
    List<Answer> fetchAnswersByFormsFilledByEmployee(Employee employee, Form form);

    /**
     * It returns a list of answers based on employee Ids
     *
     * @param employeeIds - The list of employee Ids
     * @return answers - The list with all answers given by the employees
     */
    List<Answer> retriveAnswersByEmployeesIds(List<Integer> employeeIds);

    List<Answer> retriveAnswersByForm(Form form);

}
