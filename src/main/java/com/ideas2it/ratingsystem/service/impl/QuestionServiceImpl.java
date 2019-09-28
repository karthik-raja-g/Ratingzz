package com.ideas2it.ratingsystem.service.impl;

import java.util.ArrayList;
import java.util.List; 
import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;    
import org.springframework.stereotype.Service;

import com.ideas2it.ratingsystem.dao.QuestionDao;
import com.ideas2it.ratingsystem.model.Question;
import com.ideas2it.ratingsystem.model.Form;
import com.ideas2it.ratingsystem.service.QuestionService;
import com.ideas2it.ratingsystem.service.FormService;
import com.ideas2it.ratingsystem.util.DateUtil;

/**
 * It perform the some logical operation for add the question to form and 
 * Create a json object put a question into that object
 * update,delete operations update the questions and delete the question from 
 * database 
 */
@Service  
public class QuestionServiceImpl implements QuestionService {
    
    @Autowired    
    private QuestionDao questionDao; 

    @Autowired
    private FormService formService;

    /**
     * {@inheritdoc}
     */
    public String addQuestion(int id, String newQuestion) {
        Form form = formService.getFormById(id);
        Question question = new Question();
        question.setForm(form);
        question.setCreatedDate(DateUtil.getDate());
        question.setQuestion(newQuestion);
        formService.updateForm(form, question);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("question", newQuestion);
        return jsonObject.toString();
    }   
    
    /**
     * {@inheritdoc}
     */ 
    public void deleteQuestion(int id) {
        deleteQuestion(id);
    }
    
    /**
     * {@inheritdoc}
     */
    public Question getQuestionById(int id) {
        return questionDao.getQuestionById(id);
    }
    
    /**
     * {@inheritdoc}
     */
    public void updateQuestion(Question updatedquestion) {
        int questionId = updatedquestion.getId();
        Question question = getQuestionById(questionId);
        question.setQuestion(updatedquestion.getQuestion());
        question.setModifiedDate(DateUtil.getDate());
        questionDao.updateQuestion(question);
    }
    /**
     * {@inheritdoc}
     */
    public Set<Question> createQuestionsForNewForm(Form oldForm, Form newForm) {
        Set<Question> questions = new HashSet<Question>();
        for(Question question : oldForm.getQuestions()) {
            Question newQuestion = new Question();
            newQuestion.setForm(newForm);
            newQuestion.setQuestion(question.getQuestion());
            newQuestion.setCreatedDate(DateUtil.getDate());
            newQuestion.setModifiedDate(DateUtil.getDate());
            questions.add(newQuestion);
        }
        return questions;
    }
}  
