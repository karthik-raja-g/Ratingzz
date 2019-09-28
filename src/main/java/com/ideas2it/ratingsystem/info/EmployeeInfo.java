package com.ideas2it.ratingsystem.info;

import java.util.Date;

/** 
 * EmployeeInfo used to transfer data from service to controller 
 *  
 *  Author J.Balakumaran
 *  Since 04-09-19
 *  Version 1.0.0
 */

/* 
 * EmployeeInfo used to transfer data from service to controller
 * so we use getter and setter functions to access them outside.
 */
public class EmployeeInfo {

    private Date createdDate;
    private Date modifiedDate;
    private int id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String profilePicUrl;
    private String role;
    private String reportingPerson;

    /**
    * Getter and Setter function 
    * for all variables are written seperately 
    */ 
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePicUrl() {
        return this.profilePicUrl;
    }
    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    public Date getModifiedDate() {
        return this.modifiedDate;
    }
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    
    public Date getCreatedDate() {
        return this.createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    
    public String getRole() {
        return this.role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    
    public String getReportingPerson() {
        return this.reportingPerson;
    }
    public void setReportingPerson(String reportingPerson) {
        this.reportingPerson = reportingPerson;
    }
}
