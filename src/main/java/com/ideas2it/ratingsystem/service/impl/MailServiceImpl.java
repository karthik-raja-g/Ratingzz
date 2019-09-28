package com.ideas2it.ratingsystem.service.impl;
 
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
 
import com.ideas2it.ratingsystem.model.Employee;
import com.ideas2it.ratingsystem.util.Properties;

/**
 *<p>
 * The Mail service class sends form invitations to various employees
 * based on their roles. 
 *</p>
 *
 * @author Karthik created on 10 September 2019
 */ 
@Service
public class MailServiceImpl {
 
    @Autowired
    JavaMailSender mailSender;

    @Autowired
    private Properties properties;  
                                   
    private int formId = 0;

    /**
     * It sets the form Id of the form that has to be published
     * It is done to identify the correct form to show the recipient for filling
     *
     * @param formId - The form Id of form published
     */
    public void setFormId(int formId) {
        this.formId = formId;
    }
 
    /**
     * The abstract method to send email. Since a link has to be shared
     * Mime message preparator is used.
     *
     * @param object - The object from which the mail sending details are obtained
     */
    public void sendEmail(Object object) {
 
        Employee employee = (Employee) object;
 
        MimeMessagePreparator preparator = getMessagePreparator(employee);
 
        try {
            mailSender.send(preparator);
            System.out.println("Message Sent...Hurrey");
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }
 
    /**
     * The Mime message preparator prepares the message to be sent to the employee
     *
     * @param employee - The employee to which the mail has to be sent
     * @return preparator - The prepared object containing mail sending details
     */
    private MimeMessagePreparator getMessagePreparator(final Employee employee) {
 
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
 
            public void prepare(MimeMessage mimeMessage) throws Exception {
                StringBuilder stringBuilder = new StringBuilder();
                String url = stringBuilder.append(properties.getHostUrl()).append(String.valueOf(formId)).toString();
                mimeMessage.setFrom(properties.getSenderId());
                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(employee.getEmail()));
                mimeMessage.setText(properties.getInvitation()+url);
                mimeMessage.setSubject(properties.getMailSubject());
            }
        };
        return preparator;
    } 
}
