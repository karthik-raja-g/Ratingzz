package com.ideas2it.ratingsystem.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Criterion;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;    
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;  
import org.springframework.transaction.annotation.Transactional;  

import com.ideas2it.ratingsystem.constant.Constant;
import com.ideas2it.ratingsystem.dao.AnswerDao;
import com.ideas2it.ratingsystem.model.Answer;
import com.ideas2it.ratingsystem.model.Employee;
import com.ideas2it.ratingsystem.model.Form;

/**
 *<p>
 * It retrives answers submitted by employees. The retrival of
 * anwers can be based on various condidtions
 *</p>
 *
 * @author karthik created on 7 September 2019
 */
@Repository
public class AnswerDaoImpl implements AnswerDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Autowired
    private SessionFactory sessionFactory ;

    private static final String RETRIVE_ANSWERS_BY_EMPLOYEE_IDS_QUERRY = 
        "FROM Answer answer WHERE employee.id IN (:ids)";
    private static final String EMPLOYEE_RESTRICTION = "responder";
    private static final String FORM_RESTRICTION = "form";
    private static final String IDS_AS_PARAMETER = "ids"; 

    /**
     * It saves an answer into database
     *
     * @param answer - The answer object to be saved
     */
    @Transactional
    public void insertAnswer(Answer answer) {
        hibernateTemplate.save(answer);
    }

    /**
     * It returns a answer based on user Id
     *
     * @param id - The answer Id
     * @return Answer - The answer object for the Id
     */
    @Transactional
    public Answer retriveAnswerById(int id) {
        return (Answer) hibernateTemplate.get(Answer.class,id);
    }

    /**
     * It returns list of all answers submitted by a particular employee
     * 
     * @param employee - The employee whoose respones are required
     * @return answers - The list of answers subitted by the employee
     */
    @Transactional
    public List<Answer> fetchAnswersByEmployee(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Answer.class);
        criteria.add(Restrictions.eq(EMPLOYEE_RESTRICTION,employee));
        List<Answer> answers = criteria.list();
        return answers;
    }

    /**
     * It returns list of all answers submitted by a particular employee
     * 
     * @param employee - The employee whoose respones are required
     * @return answers - The list of answers submitted by an employee for a form
     */
    @Transactional
    public List<Answer> fetchAnswersByFormsFilledByEmployee(Employee employee, Form form) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Answer.class);
        Criterion employeeCriteria = Restrictions.eq(EMPLOYEE_RESTRICTION,employee);
        Criterion formCriteria = Restrictions.eq(FORM_RESTRICTION,form);
        Criterion criterion = Restrictions.and(employeeCriteria,formCriteria);
        criteria.add(criterion);
        List<Answer> answers = criteria.list();
        return answers;
    }

    /**
     * It returns a list of answers based on employee Ids
     *
     * @param employeeIds - The list of employee Ids
     * @return answers - The list with all answers given by the list of employees
     */
    @Transactional
    public List<Answer> retriveAnswersByEmployeesIds(List<Integer> employeeIds) {
        List<Answer> answers;
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(RETRIVE_ANSWERS_BY_EMPLOYEE_IDS_QUERRY);
        query.setParameterList(IDS_AS_PARAMETER, employeeIds);
        answers = query.list();
        return answers;
    }


    /**
     * It returns a list of answers based on employee Ids
     *
     * @param employeeIds - The list of employee Ids
     * @return answers - The list with all answers given by the list of employees
     */
    @Transactional
    public List<Answer> retriveAnswersByForm(Form form) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Answer.class);
        criteria.add(Restrictions.eq("form",form));
        List<Answer> answers = criteria.list();
        return answers;
    }
}
