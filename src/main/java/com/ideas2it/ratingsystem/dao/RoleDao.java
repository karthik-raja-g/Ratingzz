package com.ideas2it.ratingsystem.dao;

import java.util.List;

import com.ideas2it.ratingsystem.model.Role;
    
/**
 * It perform insert,delete,update,display operation
 * It get the information from database
 */
public interface RoleDao {
    
    /**
     * It insert the role details into database
     *
     * @param role - it carry the role details
     */
    void insertRole(Role role);
    
    /**
     * It  all all values present in database
     *
     * @return Roles - It return all role records 
     */
    List<Role> getAllRole();
    
    /**
     * update the existing value into new value in database
     * 
     * @param role - it carry the updated values
     */
    void updateRole(Role role);
    
    /**
     * Use to get particular record field in user given input
     *
     * @param id - it carry the user input id number
     */
    Role getRoleById(int id);
    
    /**
     * Use to get particular record field in user given role name
     *
     * @param name - carry the user input name
     */
    List<Role> getRoleByName(String name);

    /**
     * It  display all values presant in database
     */
    List<Role> getAllRoleDetail();

    /**
     * Get roles based on ids
     */
    List<Role> getRolesByIds(List<Integer> ids);

}
