package com.ideas2it.ratingsystem.model;

import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.ideas2it.ratingsystem.constant.Constant;
import com.ideas2it.ratingsystem.model.Form;

/**
 *<p>
 * A question can be in one or more forms. Questions are added in the 
 * form by the admin. A question has number of options to select from 
 *</p>
 *
 * @author karthik created on 4 September 2019
 */
@Entity
@Table(name = Question.QUESTION_TABLE)
public class Question {

    public static final String QUESTION_TABLE = "QUESTION";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_QUESTION = "QUESTION";
    private static final String COLUMN_OPTION_ID = "OPTION_ID";
    private static final String COLUMN_CREATED_DATE = "CREATED_DATE";
    private static final String COLUMN_MODIFIED_DATE = "MODIFIED_DATE";
    private static final String COLUMN_FORM_ID = "FORM_ID";

    private int id;
    private String question;
    private Form form;
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

    @Column(name = COLUMN_QUESTION)
    public String getQuestion() {
        return this.question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }

    @ManyToOne
    @JoinColumn(name = COLUMN_FORM_ID)
    public Form getForm() {
        return this.form;
    }
    public void setForm(Form form) {
        this.form = form;
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
        return stringBuilder.append(id).append(question).append(form).append(createdDate).append(modifiedDate).toString();
                    
    }
}
