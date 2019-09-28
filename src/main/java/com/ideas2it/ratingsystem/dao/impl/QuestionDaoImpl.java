package com.ideas2it.ratingsystem.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.query.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;    
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;  
import org.springframework.transaction.annotation.Transactional;  
    
import com.ideas2it.ratingsystem.dao.QuestionDao;
import com.ideas2it.ratingsystem.model.Question;
    
/**
 * It perform insert,delete,update,display operation
 */ 
@Repository
public class QuestionDaoImpl implements QuestionDao {

    @Autowired 
    private HibernateTemplate template;


    /**
     * {@inheritdoc}
     */
    @Transactional
    public void updateQuestion(Question question) {
        template.update(question);  
    }
    
    /**
     * {@inheritdoc}
     */
    @Transactional
    public Question getQuestionById(int id) {
        Question question = (Question)template.get(Question.class, id);  
        return question;
    }
    
    /**
     * {@inheritance}
     */
    @Transactional
    public void removeQuestion(int id) {
        Question question = (Question)template.get(Question.class, id); 
        template.delete(question);
    }
}
        
