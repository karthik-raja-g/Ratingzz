package com.ideas2it.ratingsystem.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.ideas2it.ratingsystem.model.Role;
import com.ideas2it.ratingsystem.service.RoleService;
import com.ideas2it.ratingsystem.service.impl.RoleServiceImpl;

/**
 * TODO Exception handling and logger
 * Use to get information from users and Add into database
 * Delete tha information based on user given id
 * It update the existing values into new values
 * Get particular role information based on existing id
 * Search existing entry 
 */
@Controller  
public class RoleController {

    private static final String ADD_ROLE = "/add-role";
    private static final String CREATE_ROLE = "/create-role";
    private static final String SHOW_ROLE_ADD_FORM = "/show-role-add-form";
    private static final String REDIRECT_DISPLAY_ROLES = "redirect:/displayRoles";
    private static final String REQUEST_DISPLAY_URL = "/display-roles";
    private static final String REQUEST_GET_ROLE = "/getrole-by-id";
    private static final String REQUEST_UPDARE_ROLE = "/update-role";
    private static final String REQUEST_DELETE_ROLE = "/delete-role";
    private static final String REQUEST_DISPLAY_ROLE = "/display-role";
    private static final String ERROR = "error";
    private static final String EDIT_ROLE_JSP = "editRole";
    private static final String SET_ROLE = "role";
    private static final String SET_ROLES = "roles";
    private static final String MESSAGE = "message";
    private static final String ROLENAME_ALREADY_EXIST = 
        "rolename is already exist try different name";
    private static final String DISPLAY_ROLES = "displayRoles";
    
    @Autowired    
    private RoleService roleService;


    @RequestMapping(value = CREATE_ROLE)
    public ModelAndView createRole() {
        Role role = new Role();
        ModelAndView model = new ModelAndView("createRole");
        model.addObject(role);
        return model;
    }
    /**
     * Get input from the user
     * If role name is not exist in database new role is created
     * Otherwise it show the error page
     *
     * @param role - it carry new role information
     * @return If condition is true it redirects the display role page
     * Otherwise it display error page
     */
    @RequestMapping(value = ADD_ROLE, method = RequestMethod.POST)      
    public ModelAndView addRole(@ModelAttribute(SET_ROLE) Role role) { 
        String name = role.getName();
        if (roleService.isRoleNameExist(name)) {
            roleService.addRole(role);
            return new ModelAndView(REDIRECT_DISPLAY_ROLES);
        }
        return new ModelAndView(ERROR, MESSAGE, ROLENAME_ALREADY_EXIST);
    }
    
    /**
     * Use to display the create form for role
     *
     * return redirect the create form
     */
    @RequestMapping(value = SHOW_ROLE_ADD_FORM, method = RequestMethod.POST)
    public String showRoleAddForm() {
         return SHOW_ROLE_ADD_FORM;
    }
    
    /**
     * Display all roles information
     *
     * @param model - use to add model object
     * return redirct the display page
     */
    @RequestMapping(value = REQUEST_DISPLAY_URL, method = RequestMethod.GET)  
    public String displayRoles(Model model) {
        List<Role> roles = roleService.getAllRoleInformation();
        model.addAttribute(SET_ROLES,roles);  
        return DISPLAY_ROLES;
    }
    
    /**
     * Use to get particular record in user given input 
     *
     * @return redirects the edit page
     */
    @RequestMapping(value = REQUEST_GET_ROLE, method = RequestMethod.GET)
    public ModelAndView getRoleById(@RequestParam(Constant.ID) int id) {
        ModelAndView model = new ModelAndView();
        Role role = roleService.getRoleById(id);
        model.addObject(SET_ROLE, role);
        model.setViewName(EDIT_ROLE_JSP);
        return model;
    }
    
    /**
     * It update the existing value into user given new values
     *
     * @param role - it carry the new role values
     * @return - show role display page
     */ 
    @RequestMapping(value = REQUEST_UPDARE_ROLE, method = RequestMethod.POST)    
    public String updateRole(@ModelAttribute(SET_ROLE) Role role){    
        roleService.updateRole(role);   
        return DISPLAY_ROLES;    
    }  
 
    /**
     * It delete user information
     * If id is presant in database it change the status to false
     *
     * @param id - it carry the input id
     * @return - show role display page
     */
    @RequestMapping(value = REQUEST_DELETE_ROLE, method = RequestMethod.POST)    
    public String delete(@RequestParam(Constant.ID) int id) { 
        roleService.deleteRole(id);    
        return DISPLAY_ROLES;
    }   
    
    /**
     * It display all roles 
     */
    @RequestMapping(value = REQUEST_DISPLAY_ROLE, method = RequestMethod.POST)
    public ModelAndView displayRoles() {
        ModelAndView model = new ModelAndView();
        List<Role> roles = new ArrayList();
        roles = roleService.getAllRoleInformation();
        model = new ModelAndView();
        model.addObject(SET_ROLES, roles);
        model.setViewName(DISPLAY_ROLES);
        return model;   
    }
}
