package com.ideas2it.ratingsystem.util;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

@Component("properties")
public class Properties {

    @Value("${mail.host}")
    private String hostUrl;
    
    @Value("${mail.invitation}")
    private String invitation;

    @Value("${mail.subject}")
    private String mailSubject;
    
    @Value("${mail.sender}")
    private String senderId;
    
    public String getHostUrl() {
        return hostUrl;
    }
    
    public String getInvitation() {
        return invitation;
    }

    public String getMailSubject() {
        return mailSubject;
    }
    
    public String getSenderId() {
        return senderId;
    }
}
