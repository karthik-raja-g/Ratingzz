package com.ideas2it.ratingsystem.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.ideas2it.ratingsystem.model.Employee;

/**
 *<p>
 * It retrives the user currently logged in an active session. 
 *</p>
 *
 * @author karthik created on 20 August 2019
 */
public class SessionEmployeeProvider {

    public static Employee getSessionEmployee() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            return (Employee)authentication.getPrincipal();
    } 
} 
        
