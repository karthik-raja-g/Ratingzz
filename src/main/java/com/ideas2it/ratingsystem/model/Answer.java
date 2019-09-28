package com.ideas2it.ratingsystem.model;

import java.util.Date;
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

import com.ideas2it.ratingsystem.constant.Constant;
import com.ideas2it.ratingsystem.model.Employee;
import com.ideas2it.ratingsystem.model.Form;
import com.ideas2it.ratingsystem.model.Question;

/**
 *<p>
 * An answer has its question,form and responder reference in it, making
 * it easier to access various respones. A comment can be optional for an 
 * answer
 *</p>
 *
 * @author karthik created on 4 September 2019
 */
@Entity
@Table(name = Answer.ANSWER_TABLE)
public class Answer {

    public static final String ANSWER_TABLE = "ANSWER";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_ANSWER = "ANSWER";
    private static final String COLUMN_CREATED_DATE = "CREATED_DATE";
    private static final String COLUMN_MODIFIED_DATE = "MODIFIED_DATE";
    private static final String COLUMN_COMMENTS = "COMMENTS";
    private static final String COLUMN_QUESTION_ID = "QUESTION_ID";
    private static final String COLUMN_RESPONDER_ID = "RESPONDER_ID";
    private static final String COLUMN_FORM_ID = "FORM_ID";

    private int id;
    private int answer;
    private String comments;
    private Question question;
    private Form form;
    private Employee responder;
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

    @Column(name = COLUMN_ANSWER)
    public int getAnswer() {
        return this.answer;
    }
    public void setAnswer(int answer) {
        this.answer = answer;
    }

    @Column(name = COLUMN_COMMENTS)
    public String getComments() {
        return this.comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = COLUMN_QUESTION_ID)
    public Question getQuestion() {
        return this.question;
    }
    public void setQuestion(Question question) {
        this.question = question;
    }

    @ManyToOne
    @JoinColumn(name = COLUMN_RESPONDER_ID)
    public Employee getResponder() {
        return this.responder;
    }
    public void setResponder(Employee responder) {
        this.responder = responder;
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
    * It returns the ANSWER information in string format
    *
    * @return    String    The string containing ANSWER details
    */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append(id).append(answer).append(comments).append(question).append(form).append(createdDate)
                    .append(modifiedDate).toString();
    }
}
