package com.ideas2it.ratingsystem.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;    
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;  
import org.springframework.transaction.annotation.Transactional;  
    
import com.ideas2it.ratingsystem.dao.RoleDao;
import com.ideas2it.ratingsystem.model.Role;
    
/**
 * It perform insert,delete,update,display operation
 */ 
@Repository
public class RoleDaoImpl implements RoleDao {
    
    private static final String SET_PARAMETER_NAME = "name";
    private static final String GET_ROLES_BY_IDS_QUERY =  "FROM Role role WHERE role.id IN (:ids)";
    private static final String SET_IDS = "ids";
    
    @Autowired 
    private HibernateTemplate template;

    @Autowired 
    private SessionFactory sessionFactory;
    
    /**
     * {@inheritdoc}
     */
    @Transactional
    public void insertRole(Role role) {
        template.save(role);  
    }
    
    /**
     * {@inheritdoc}
     */
    @Transactional
    public List<Role> getAllRole() {
        List<Role> roles = new ArrayList<Role>();  
        roles = template.loadAll(Role.class); 
        return roles;
    }
    
    /**
     * {@inheritdoc}
     */
    @Transactional
    public void updateRole(Role role) {
        template.update(role);  
    }
    
    /**
     * {@inheritdoc}
     */
    @Transactional
    public Role getRoleById(int id) {
        Role role = (Role)template.get(Role.class,id);  
        return role;
    }
    
    /**
     * {@inheritdoc}
     */
    @Transactional
    public List<Role> getRoleByName(String name) {
        //SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Role> roles = new ArrayList();
        Criteria criteria = session.createCriteria(Role.class);
        criteria.add(Restrictions.like(SET_PARAMETER_NAME, name));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        roles = criteria.list();
        return roles;
    }
    
    /**
     * {@inheritdoc}
     */
    @Transactional
    public List<Role> getAllRoleDetail() {
        List<Role> roles = new ArrayList<Role>();  
        roles = template.loadAll(Role.class);  
        return roles;
    }

    /**
     * {@inheritance}
     */
    @Transactional
      public List<Role> getRolesByIds(List<Integer> ids) {
        List<Role> roles;
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(GET_ROLES_BY_IDS_QUERY);
        query.setParameterList(SET_IDS, ids);
        roles = query.list();
        return roles;
    }
}
