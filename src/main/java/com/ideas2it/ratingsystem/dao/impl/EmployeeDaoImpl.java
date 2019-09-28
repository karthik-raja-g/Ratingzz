package com.ideas2it.ratingsystem.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Session; 
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ideas2it.ratingsystem.constant.Constant;
import com.ideas2it.ratingsystem.model.Employee;
import com.ideas2it.ratingsystem.model.Role;
import com.ideas2it.ratingsystem.dao.EmployeeDao;


/**
 *<p>
 * It adds,deletes,updates Employees from database. 
 * It can retrive Employee information from the database
 *</p>
 *
 * @author  J.Balakumaran   created on 5 Sep 2019
 *
 */ 
@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;
    
    @Autowired
    private SessionFactory sessionFactory;
   
    private static final String IS_ACTIVE = "isActive";
    
    /**
     * {@inheritdoc}
     */
    @Override
    @Transactional
    public int totalIdInTable() {
        int size = Constant.NUMERIC_ZERO;
        Session session = sessionFactory.openSession();        
        Criteria criteria = session.createCriteria(Employee.class);
        criteria.add(Restrictions.eq(IS_ACTIVE, Boolean.TRUE));
        criteria.setProjection(Projections.rowCount());
        size = ((Number) criteria.uniqueResult()).intValue();
        return size;
    }
    
    /**
     * {@inheritdoc}
     */
    @Override
    @Transactional
    public Employee insertEmployee(Employee employee) {
        int employeeId = (Integer) hibernateTemplate.save(employee);
        employee.setId(employeeId);
        return employee;
    }
    
    /**
     * {@inheritdoc}
     */
    @Override
    @Transactional
    public List<Employee> retriveEmployees(int offSet) {
        List<Employee> employees;
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Employee.class);
        criteria.add(Restrictions.eq(IS_ACTIVE, Boolean.TRUE));
        criteria.setFirstResult(offSet);
        criteria.setMaxResults(Constant.NO_OF_RECORDS_PER_PAGE);
        employees = criteria.list();
        return employees;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    @Transactional
    public boolean updateEmployee(Employee employee) {
        boolean status = Boolean.TRUE;
        try {
            hibernateTemplate.update(employee);
        } catch (Exception e) {
            //TODO 
            status = Boolean.FALSE;
        }    
        return status;
    }
    
    /**
     * {@inheritdoc}
     */
    @Override  
    @Transactional
    public Employee retriveEmployeeById(int employeeId) {
        return (Employee) hibernateTemplate.get(Employee.class,employeeId);
    }
    
    /**
     * {@inheritdoc}
     */
    @Override
    @Transactional
    public Employee retriveEmployeeByUsername(String username) {
        Employee employee;
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Employee.class);
        employee = (Employee) criteria.add(Restrictions.eq(
                        Constant.USERNAME,username)).uniqueResult();
        return employee;
    }
    
    /**
     * {@inheritdoc}
     */
    @Override
    @Transactional
    public List<Employee> retriveSubordinates(Employee employee) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Employee.class);
        criteria.add(Restrictions.eq(Constant.REPORTING_PERSON,employee));
        List<Employee> employees = criteria.list();
        return employees;
    }
    
    /**
     * {@inheritdoc}
     */
    @Override
    @Transactional
    public List<Employee> retriveEmployeesByRole(Role role) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Employee.class);
        criteria.add(Restrictions.eq(Constant.ROLE,role));
        List<Employee> employees = criteria.list();
        return employees;
    }
    
    /**
     * {@inheritdoc}
     */
    @Override
    @Transactional
    public List<Employee> getAllEmployees() {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Employee.class);
        criteria.add(Restrictions.eq(IS_ACTIVE, Boolean.TRUE));
        List<Employee> employees = criteria.list();
        return employees;
    }
}
