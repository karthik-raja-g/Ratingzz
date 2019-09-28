package com.ideas2it.ratingsystem.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;    
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;  
import org.springframework.transaction.annotation.Transactional;    
    
import com.ideas2it.ratingsystem.dao.FormDao;
import com.ideas2it.ratingsystem.model.Form;
import com.ideas2it.ratingsystem.model.Employee;
    
/**
 * It perform insert,delete,update,display operation
 */ 
@Repository
public class FormDaoImpl implements FormDao {

    private static final String GET_FORMS_BY_IDS_QUERY =  "FROM Form form WHERE form.id IN (:ids)";
    private static final String SET_IDS = "ids";

    @Autowired 
    private HibernateTemplate template;
    
    @Autowired 
    private SessionFactory sessionFactory;
    

    /**
     * {@inheritdoc}
     */
    @Transactional
    public Form insertForm(Form form) {
        int id = (Integer) template.save(form);
        form.setId(id);
        return form;  
    }
    
    /**
     * {@inheritdoc}
     */
    @Transactional
    public Form updateForm(Form form) {
        template.update(form);
        return form;
    }
    
    /**
     * {@inheritdoc}
     */
    @Transactional
    public Form getFormById(int id) {
        Form form = (Form)template.get(Form.class, id);  
        return form;
    }

    /**
     * {@inheritance}
     */
      @Transactional
      public List<Form> getFormsByIds(List<Integer> ids) {
        List<Form> forms;
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(GET_FORMS_BY_IDS_QUERY);
        query.setParameterList(SET_IDS, ids);
        forms = query.list();
        return forms;
    }

    @Transactional
    public List<Form> retriveAllForms() {
        List<Form> forms = new ArrayList<Form>();  
        forms = template.loadAll(Form.class); 
        return forms;
    }

    /**
     * {@inheritance}
     */
      @Transactional
      public List<Form> getFormByCreator(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Form.class);
        criteria.add(Restrictions.eq("creator",employee));
        List<Form> forms = criteria.list();
        return forms;
    }
}
