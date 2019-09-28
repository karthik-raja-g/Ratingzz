package com.ideas2it.ratingsystem.model;

import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ideas2it.ratingsystem.constant.Constant;
import com.ideas2it.ratingsystem.model.Role;

/**
 *<p>
 * An employee is the focus point of the rating system. An admin, 
 * who is also an employee, is responsible for creating forms. All
 * other employees have varied access to forms based on their role
 * Every employee can view his/her responses.
 *</p>
 *
 * @author karthik created on 4/09/19
 */
@Entity
@Table(name = Employee.EMPLOYEE_TABLE)
public class Employee {

    public Employee () {
        this.isActive = Boolean.TRUE;
    }

    public static final String EMPLOYEE_TABLE = "EMPLOYEE";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_NAME = "NAME";
    private static final String COLUMN_USERNAME = "USERNAME";
    private static final String COLUMN_PASSWORD = "PASSWORD";
    private static final String COLUMN_EMAIL = "EMAIL_ID";
    private static final String COLUMN_IS_ACTIVE = "IS_ACTIVE";
    private static final String COLUMN_REPORTING_PERSON_ID = "REPORTING_PERSON_ID";
    private static final String COLUMN_ROLE_ID = "ROLE_ID";
    private static final String COLUMN_DP_URL = "DP_URL";
    private static final String COLUMN_CREATED_DATE = "CREATED_DATE";
    private static final String COLUMN_MODIFIED_DATE = "MODIFIED_DATE";

    private int id;
    private String name;
    private String username;
    private String password;
    private String email;
    private Boolean isActive;
    private Employee reportingPerson;
    private Role role;
    private String profilePicUrl;
    private Date createdDate;
    private Date modifiedDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_ID)
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = COLUMN_NAME)
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = COLUMN_USERNAME)
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = COLUMN_PASSWORD)
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = COLUMN_EMAIL)  
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = COLUMN_IS_ACTIVE)
    public Boolean getIsActive() {
        return this.isActive;
    }
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = COLUMN_REPORTING_PERSON_ID)
    public Employee getReportingPerson() {
        return this.reportingPerson;
    }
    public void setReportingPerson(Employee reportingPerson) {
        this.reportingPerson = reportingPerson;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = COLUMN_ROLE_ID)
    public Role getRole() {
        return this.role;
    }
    public void setRole(Role role) {
    this.role = role;
    }

    @Column(name = COLUMN_DP_URL)  
    public String getProfilePicUrl() {
        return this.profilePicUrl;
    }
    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    @Column(name = COLUMN_CREATED_DATE)
    public Date getCreatedDate() {
        return this.createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Column(name = COLUMN_MODIFIED_DATE)
    public Date getModifiedDate() {
        return this.modifiedDate;
    }
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }


    /**
    * It returns the employee information in string format
    *
    * @return    String    The string containing employee details
    */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append(id).append(name).append(username).append(password).append(email).append(isActive)
                    .append(role).append(createdDate).append(modifiedDate).toString();
    } 

}
