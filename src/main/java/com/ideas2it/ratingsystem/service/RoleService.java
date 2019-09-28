package com.ideas2it.ratingsystem.service;

import java.util.List;

import com.ideas2it.ratingsystem.model.Role;

/**
 * It perform the some logical operation for created date and checking if role is 
 * Exist or not and perform add ,delete,update,get all information for role
 */
public interface RoleService {
    
    /**
     * Add role information
     *
     * @param role - it carry the role details
     */
    void addRole(Role role);
    
    /**
     * Get all role records
     *
     * @return Roles - It return the all role records present in database
     */
    List<Role> getAllRoleInformation();
    
    /**
     * check the id is already exist or not
     *
     * @param id - it carry the user input id
     * @return true - id exist in database
     * @return false - id not exist in database
     */
    boolean isRoleIdExist(int id);
    
    /**
     * get the remove id number
     * based on id it set the status is false
     *
     * @param id -it carry the user input id
     */
    void deleteRole(int id);
    
    /**
     * It update role information replaced by new value
     *
     * @param role - it carry the updated records
     */
    void updateRole(Role role);
    
    /**
     * Use to get particular record in user given input
     *
     * @param id - it carry the user given id
     * @return it return the particular record field information
     */
    Role getRoleById(int id);
    
    /**
     * Check the name is already exist or not 
     * @return true the role name is present in data base
     * @return false role name not exist
     *
     * @param name - carry the user input name
     */
    boolean isRoleNameExist(String name);

    
    /**
     * Get roles based on ids
     */
    List<Role> getRolesByIds(String[] ids);
}


