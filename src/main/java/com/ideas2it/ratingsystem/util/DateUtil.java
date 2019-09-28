package com.ideas2it.ratingsystem.util;

import java.text.SimpleDateFormat; 
import java.util.Date;

import com.ideas2it.ratingsystem.constant.Constant;

/**
 * Get the current system date and time in dd:mm:yyyy hh:mm:ss format
 */
public class DateUtil {

    /**
     * Get the current system date and time
     *
     * return simpleDateFormat - return current system date and time
     */ 
    public static Date getDate() {
        Date date = null;
        try {
            date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constant.DATE_FORMAT);  
            date = simpleDateFormat.parse(simpleDateFormat.format(date));
        } catch(Exception e) {
            //TODO
            
        }
        return date;
    }
}
