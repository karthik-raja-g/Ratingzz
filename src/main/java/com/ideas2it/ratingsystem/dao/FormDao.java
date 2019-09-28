package com.ideas2it.ratingsystem.dao;

import java.util.List;

import com.ideas2it.ratingsystem.model.Form;
import com.ideas2it.ratingsystem.model.Employee;
    
/**
 * It perform insert,delete,update,display operation
 * It get the information from database
 */
public interface FormDao {
    
    /**
     * It insert the form details into database
     *
     * @param role - it carry the role details
     * @return form - return form details include the form id
     */
    Form insertForm(Form form);
    
    /**
     * It update the form old values into new values
     * 
     * @param form - it carry the new form values for update
     */
    Form updateForm(Form form);
    
    /**
     * Get particular form information based on the form id
     *
     * @param id - carry the form id
     * @return return the form details 
     */
    Form getFormById(int id);

    /**
     * It returns a list of forms based on form Ids
     *
     * @param formIds - The list of form Ids
     * @return - List of forms based on form Ids
     */
    List<Form> getFormsByIds(List<Integer> formIds);
    List<Form> getFormByCreator(Employee employee);
    List<Form> retriveAllForms() ;
}
