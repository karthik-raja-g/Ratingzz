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
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.ideas2it.ratingsystem.constant.Constant;
import com.ideas2it.ratingsystem.model.Employee;
import com.ideas2it.ratingsystem.model.Question;
import com.ideas2it.ratingsystem.model.Role;

/**
 *<p>
 * A form has a set of objective type questions i.e a question
 * has number of options from which the user has to select any
 * one. Any form is created only by admin. The form can be published 
 * to employees, based on their roles. The admin specifies the roles
 * that can answer a form. The employee with higher role access can view
 * the responses of all their lower level employees.
 *</p>
 *
 * @author karthik created on 4 September 2019
 */
@Entity
@Table(name = Form.FORM_TABLE)
public class Form { 

    public static final String FORM_TABLE = "FORM";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_TITLE = "TITLE";
    private static final String COLUMN_DESCRIPTION = "DESCRIPTION";
    private static final String COLUMN_QUESTION_ID = "QUESTION_ID";
    private static final String COLUMN_CREATOR_ID = "CREATOR_ID";
    private static final String COLUMN_STATUS = "STATUS";
    private static final String COLUMN_CREATED_DATE = "CREATED_DATE";
    private static final String COLUMN_MODIFIED_DATE = "MODIFIED_DATE";
    private static final String COLUMN_ROLE_ID = "ROLE_ID";
    private static final String JOIN_FORM_ROLE_TABLE = "FORM_ROLES";
    private static final String JOIN_TABLE_FORM_COLUMN = "FORM_ID";
    private static final String JOIN_TABLE_ROLE_COLUMN = "ROLE_ID"; 

    private int id;
    private String title;
    private String description;
    private Set<Question> questions; 
    private Set<Role> roles;
    private Employee creator;
    private Boolean status;
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

    @Column(name = COLUMN_TITLE)
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = COLUMN_DESCRIPTION)
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(cascade = CascadeType.ALL)
    public Set<Question> getQuestions() {
        return this.questions;
    }
    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = JOIN_FORM_ROLE_TABLE ,joinColumns = {@JoinColumn(name = JOIN_TABLE_FORM_COLUMN) },inverseJoinColumns = 
                                             {@JoinColumn(name = JOIN_TABLE_ROLE_COLUMN)})
    public Set<Role> getRoles() {
        return this.roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = COLUMN_CREATOR_ID)
    public Employee getCreator() {
        return this.creator;
    }
    public void setCreator(Employee creator) {
        this.creator = creator;
    }

    @Column(name = COLUMN_STATUS)
    public Boolean getStatus() {
        return this.status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
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
    * It returns the form information in string format
    *
    * @return    String    The string containing form details
    */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append(id).append(title).append(description).append(creator)
                    .append(createdDate).append(modifiedDate).toString();
    }
}
