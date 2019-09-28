package com.ideas2it.ratingsystem.service;

import java.util.Set;
import com.ideas2it.ratingsystem.model.Question;
import com.ideas2it.ratingsystem.model.Form;

/**
 * It perform the some logical operation for add the question to form and 
 * Create a json object put a question into that object
 * update,delete operations update the questions and delete the question from 
 * database 
 */
public interface QuestionService {
    
    /**
     * Add question into form
     *
     * @param id - it carry the form id
     * @param question - it store and carry the current question information
     * @return - It return the question for json object
     */
    String addQuestion(int id, String question);
    
    /**
     * Delete the question based on id
     * 
     * @param Id - it carry deleted id
     */
    void deleteQuestion(int id);
    
    /**
     * Get question based on the id
     *
     * @param id - it carry the question id
     */
    Question getQuestionById(int id);
    
    /**
     * It update the existing value into new value
     *
     * @param question - it carry the new question value
     */
    void updateQuestion(Question updatedquestion) ;
    
    /**
     * It create a new question for using existion questions
     *
     * @param formId - it carry the form id
     * @param newForm - it carry the new form information
     * @return questions - it return the new questions into form
     */
    Set<Question> createQuestionsForNewForm(Form oldForm, Form newForm);
}
