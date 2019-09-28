package com.ideas2it.ratingsystem.service.impl;

import java.util.ArrayList;
import java.util.List; 
    
import org.springframework.beans.factory.annotation.Autowired;    
import org.springframework.stereotype.Service;  

import com.ideas2it.ratingsystem.constant.Constant;
import com.ideas2it.ratingsystem.dao.RoleDao;
import com.ideas2it.ratingsystem.dao.impl.RoleDaoImpl;
import com.ideas2it.ratingsystem.model.Role;
import com.ideas2it.ratingsystem.service.RoleService;
import com.ideas2it.ratingsystem.util.DateUtil;

/**
 * It perform the some logical operation for created date and checking if role is 
 * Exist or not and perform add ,delete,update,get all information for role
 */
@Service  
public class RoleServiceImpl implements RoleService {
    
    @Autowired    
    private RoleDao roleDao;    
    
    /**
     * {@inheritdoc}
     */
    public void addRole(Role role) {
        role.setIsActive(Boolean.TRUE);
        role.setCreatedDate(DateUtil.getDate());
        roleDao.insertRole(role);
    }
    
    /**
     * {@inheritdoc}
     */
    public Role getRoleById(int id) {
        return roleDao.getRoleById(id);
    }
    
    /**
     * {@inheritdoc}
     */
    public void deleteRole(int id) {
        Role role = getRoleById(id);
        role.setIsActive(Boolean.FALSE);
        updateRole(role);
    }
    
    /**
     * {@inheritdoc}
     */
    public boolean isRoleIdExist(int id) {
        Role role = new Role();
        role = roleDao.getRoleById(id);
        if (null == role) {
            return Boolean.FALSE;
        } 
        return Boolean.TRUE;
    }
    
    /**
     * {@inheritdoc}
     */
    public void updateRole(Role role) {
        role.setModifiedDate(DateUtil.getDate());
        roleDao.updateRole(role);
    }
    
    /**
     * {@inheritdoc}
     */
    public boolean isRoleNameExist(String name) {
        Role role = new Role();
        List<Role> roles = new ArrayList();
        roles = roleDao.getRoleByName(name);
        if (Constant.NUMERIC_ZERO == roles.size()) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    /**
     * {@inheritdoc}
     */
    public List<Role> getAllRole() {
        List<Role> roles = new ArrayList();
        roles = roleDao.getAllRole();
        return roles;
    }

    /**
     * {@inheritdoc}
     */
    public List<Role> getAllRoleInformation() {
        return roleDao.getAllRoleDetail();
    }
    
    /**
     * {@inheritdoc}
     */
    public List<Role> getRolesByIds(String[] ids) {
        List<Integer> listOfIds = new ArrayList();
        for (String id : ids) {
            listOfIds.add(Integer.parseInt(id));
        }
        return roleDao.getRolesByIds(listOfIds);
    }
        
}
    
    
        
