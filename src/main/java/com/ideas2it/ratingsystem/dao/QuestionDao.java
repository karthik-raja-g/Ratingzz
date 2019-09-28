package com.ideas2it.ratingsystem.dao;

import com.ideas2it.ratingsystem.model.Question;
import com.ideas2it.ratingsystem.model.Role;
    
/**
 * It perform insert,delete,update,display operation
 * It get the information from database
 */
public interface QuestionDao {
    
    /**
     * update the existing value into new value in database
     * 
     * @param role - it carry the updated values
     */
    void updateQuestion(Question question);
    
    /**
     * Use to get particular record field in user given input
     *
     * @param id - it carry the user input id number
     */
    Question getQuestionById(int id);
    
    /**
     * It delete the question from database based on the question id
     *
     * @param id - it carry the id
     */
    void removeQuestion(int id);
}
