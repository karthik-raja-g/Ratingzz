package com.ideas2it.ratingsystem.controller;

import java.io.IOException;
import javax.servlet.ServletException; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpSession; 
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;
import com.ideas2it.ratingsystem.util.SessionEmployeeProvider;
import com.ideas2it.ratingsystem.model.Employee;

@Controller
public class HomeController {

    @RequestMapping(value = "/login")
    public ModelAndView login () {
        System.out.println("@@@@@@@@@@@");
        return new ModelAndView("login");
    }

    /**
     * It prompts the user when wrong credentials are entered
     *
     * @param request 
     * @param response
     * @return model The object containing redirection information and error message
     */
    @RequestMapping(value = "/loginError")
    public ModelAndView loginError () {
        ModelAndView model = new ModelAndView("login");
        model.addObject("error",true);
        model.addObject("loginError", "Invalid username or password");
        return model;
    }

    /**
     * It shows the main menu once the user has successfully logged in
     *
     * @param request 
     * @param response
     * @return model The object containing redirection information and error message
     */
    @RequestMapping(value = "/employee-home")
    public ModelAndView displayMainMenu (HttpServletRequest request, HttpServletResponse response,HttpSession session) throws
                                                ServletException,IOException {
        Employee employee = SessionEmployeeProvider.getSessionEmployee();
        switch (employee.getRole().getName()) {
            case "ROLE_ADMIN" :
                session.setAttribute("role","ROLE_ADMIN");
                return new ModelAndView("adminHome","role","ROLE_ADMIN");
            case "ROLE_MANAGER" :
                session.setAttribute("role","ROLE_MANAGER");
                return new ModelAndView("adminHome","role","ROLE_MANAGER");
            case "ROLE_TEAM_LEADER" :
                session.setAttribute("role","ROLE_TEAM_LEADER");
                return new ModelAndView("adminHome","role","ROLE_TEAM_LEADER");
            case "ROLE_EMPLOYEE" :
                session.setAttribute("role","ROLE_EMPLOYEE");
                return new ModelAndView("adminHome","role","ROLE_EMPLOYEE");
        }
        return new ModelAndView("adminHome","role","ROLE_EMPLOYEE");        
    } 
        
}

