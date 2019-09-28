package com.ideas2it.ratingsystem.service;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.ideas2it.ratingsystem.model.Form;
import com.ideas2it.ratingsystem.model.Question;
import com.ideas2it.ratingsystem.util.AppException;
/**
 * It perform the some logical operation for created date 
 * and perform add ,delete,update,get all information for role
 */
public interface FormService {
    
    /**
     * Add form name and description for form
     *
     * @param form - it carry the form details
     * @param ids - it carry the ids for specified roles
     * @return form - return form details include form id
     */
    Form addForm(Form form, String[] ids);
    
    /**
     * Get particular form information based on the form id
     *
     * @param id - carry the form id
     * @return return the form details 
     */
    Form getFormById(int id);
    
    /**
     * It saves updated questions of the form into database
     *
     * @param form - The updated form object
     * @param roleIds - The role Ids to which the form has to be published
     */
    void updateFormQuestions(Form form);
    
    Form createFormFromExistingForm(Form form,int formId,String roleIds[]);

    void checkEmployeeForResponse(int formId) throws AppException;
    
    /**
     * It saves updated details of the form into database
     *
     * @param form - The updated form object
     * @param roleIds - The role Ids to which the form has to be published
     */
    void updateFormDetails(Form form, String[] roleIds);
    
    void updateForm(Form form, Question question);
    
    /**
     * It publishes a form to number of employees. The condition is
     * matched by the roles specified in the form and employee role
     *
     * @param formId - The form Id of form to be published
     */
    void publishForm(int formId);

    List<Form> getFormsByIds(List<Integer> formIds);

    /**
     * It validates if an employee has an invitaion to fill a particular
     * form, based on the roles specified by the form
     *
     * @param formId - THe form Id of form needed for response
     * @return form - The form for response
     * @exception - If the employee is not authorized to fill the form
     */
    //Form getFormForResponse(int formId);

    /**
     * It returns a map of questions and the overall rating count it each question
     * got from the responders.
     *
     * @param formId - The formId of form for which the stats are required
     * @return formStats - The map containig the stats about each question of the form
     */
    public Map<Integer,Map<Integer,Integer>> getFormStats(int formId);

    List<Form> getAllForms();

    Set<Form> getFormsByEmployeeId(int employeeId);
}

