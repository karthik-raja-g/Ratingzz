package com.ideas2it.ratingsystem.security;
 
import java.util.Collection;
import java.util.ArrayList;
import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.ideas2it.ratingsystem.model.Role;
import com.ideas2it.ratingsystem.model.Employee;
import com.ideas2it.ratingsystem.service.EmployeeService;
import com.ideas2it.ratingsystem.util.AppException;
/**
 *<p>
 * It provides security to the shopping module. The class acts as authentication
 * provider i.e it allows users with certain privilages to access the store. When
 * a user tries to access restricted pages, he/she has to first provide the 
 * credentials, which is checked here and then the permission is given accordingly
 *</p>
 *
 * @author    karthik  created on 22 August 2019
 */
@Component("customAuthenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider {
 
    @Autowired
    public EmployeeService employeeService;

    /**
     * The method acts as a filter for the app. Only employees with valid credentials
     * will be able to log in.
     *
     * @param authentication - The authentication object with the username and password given at login
     * @return Authentication - A token which has employee details with authorized role. THe token
     *                          is used to maintain session
     */
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        List<Role> roles = new ArrayList<Role>();
        String username = "";
        String password = "";
        Employee employee = new Employee();
        Collection<? extends GrantedAuthority> authorities = null;
        try {
            username = authentication.getName();
            password = (String) authentication.getCredentials();
            System.out.println(username);
            System.out.println(password);
            employee = employeeService.fetchEmployeeByUsername(username);
            System.out.println(employee);
            System.out.println(employee.getRole());
            checkPasswordForLogin(password,employee);
            roles.add(employee.getRole());
            System.out.println(roles);
            authorities = roles;
        } catch (Exception ex) {
            //ex.printStackTrace();
            throw new BadCredentialsException(ex+" . Login failed. Please try again");
        }
            return new UsernamePasswordAuthenticationToken(employee, password, authorities);
    }

    /**
     * It checks if password of user matches the password entered by him/her during login
     *
     * @param          password          The input password
     * @param          user              The user to check
     * @exception      UserException     - If the password is incorrect
     */
    private void checkPasswordForLogin(String password, Employee employee) throws AppException {
        //String hash = Encryptor.getEncryptedText(password);
        if(!employee.getPassword().equals(password))
            throw new AppException("Incorrect password");
    }
 
    public boolean supports(Class<? extends Object> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}

