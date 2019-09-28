package com.ideas2it.ratingsystem.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;    
import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;  
import org.springframework.web.bind.annotation.ModelAttribute;    
import org.springframework.web.bind.annotation.PathVariable;    
import org.springframework.web.bind.annotation.RequestMapping;    
import org.springframework.web.bind.annotation.RequestMethod; 
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.servlet.ModelAndView;  

import com.ideas2it.ratingsystem.constant.Constant;
import com.ideas2it.ratingsystem.model.Question;
import com.ideas2it.ratingsystem.service.QuestionService;

/**
 * Use to get information from users and Add into database
 * Delete tha information based on user given id
 * It update the existing values into new values
 * Get particular Question information based on existing id
 * Search existing entry 
 */
@Controller  
public class QuestionController {

    private static final String APPLICATION_JSON = "application/json";    
    private static final String REQUEST_ADD_QUESTION = "add-question";
    private static final String SET_QUESTION = "question";
    private static final String EDIT_QUESTION_JSP = "editQuestion";
    private static final String REQUEST_DELETE_QUESTION = "/delete-question";
    private static final String REQUEST_UPDATE_QUESTION = "/update-question";
    private static final String REQUEST_GET_QUESTION = "/getquestion-by-id";
    private static final String REDIRECT_DISPLAY_FORM = "redirect:displayForm";
    
    @Autowired    
    private QuestionService questionService;
    
    /**
     * Get Question from the user
     * It also get a form id for specified question
     * TODO Error page
     *
     * @param id - carry the form id
     * @param question - carry the question
     */
    @RequestMapping(value = REQUEST_ADD_QUESTION)      
    public void addQuestion(@RequestParam(Constant.FORM_ID) int id,@RequestParam(Constant.QUESTION) String question, HttpServletResponse response) throws IOException {

        response.setCharacterEncoding("UTF-8"); 
        System.out.println("##################"+question+id);
        String existQuestion = questionService.addQuestion(id, question);
        System.out.println("##################"+question+id);
        response.setContentType(APPLICATION_JSON);
        response.getWriter().write(existQuestion);
    }
    
    /**
     * Delete the question based on id
     * 
     * @param listOfId - it carry deleted ids
     */
    @RequestMapping(value = REQUEST_DELETE_QUESTION, method = RequestMethod.POST)
    public ModelAndView deleteQuestion(@RequestParam(Constant.ID) int id) {
        questionService.deleteQuestion(id);
        return new ModelAndView(REDIRECT_DISPLAY_FORM);   
    }

    /**
     * It update the existing value into user given new values
     *
     * @param role - it carry the new role values
     * @return - show form display page
     */ 
    @RequestMapping(value = REQUEST_UPDATE_QUESTION, method = RequestMethod.POST)    
    public ModelAndView updateQuestion(@ModelAttribute(SET_QUESTION) Question question) {    
        questionService.updateQuestion(question);
        return new ModelAndView(REDIRECT_DISPLAY_FORM);
    }  
    
    /**
     * Use to get particular record in user given input 
     *
     * @return redirects the edit page
     */
    @RequestMapping(value = REQUEST_GET_QUESTION, method = RequestMethod.GET)
    public ModelAndView getQuestionById(@RequestParam(Constant.ID) int id) {
        ModelAndView model = new ModelAndView();
        Question question = questionService.getQuestionById(id);
        model.addObject(SET_QUESTION, question);
        model.setViewName(EDIT_QUESTION_JSP);
        return model;
    }
}
        
