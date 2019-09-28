package com.ideas2it.ratingsystem.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;

import com.ideas2it.ratingsystem.constant.Constant;
/**
 *<p>
 * A role has a name, Id and description . Each role has 
 * different accessibility to the rating platform. An employee
 * will be assigned a role, based on which his/her access to 
 * the app will vary. Initially ROLE_ADMIN,ROLE_MANAGER,
 * ROLE_TEAMLEAD, ROLE_EMPLOYEE, with decreasing authority
 *</p>
 *
 * @author karthik created on 04 September 2019
 *
 */
@Entity
@Table(name = Role.ROLE_TABLE)
public class Role implements GrantedAuthority {

    public static final String ROLE_TABLE = "ROLE";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_NAME = "NAME";
    private static final String COLUMN_DESCRIPTION = "DESCRIPTION";
    private static final String COLUMN_IS_ACTIVE = "IS_ACTIVE";
    private static final String COLUMN_CREATED_DATE = "CREATED_DATE";
    private static final String COLUMN_MODIFIED_DATE = "MODIFIED_DATE";

    private int id;
    private String name;
    private String description;
    private boolean isActive;
    private Date createdDate;
    private Date modifiedDate;

    /**
    * getter and setter methods of the role fields
    */
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

    @Column(name = COLUMN_DESCRIPTION)
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = COLUMN_IS_ACTIVE)
    public Boolean getIsActive() {
        return this.isActive;
    }
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
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

    @Transient
    public String getAuthority() {
      return this.name;
    }

    /**
    * It returns the role information in string format
    *
    * @return    String    The string containing role details
    */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append(name).append(id).append(description).append(createdDate).append(modifiedDate).append(isActive).toString();
    } 
    
}
