package com.ideas2it.ratingsystem.info;

import java.util.List;
import java.util.Date;

/** playerInfo used to transfer data from service to controller 
 *  
 *  Author J.Balakumaran
 *  Since 04-06-19
 *  Version 1.0.0
 */

/* 
 * playerInfo used to transfer data from service to controller
 * so we use getter and setter functions to access them outside.
 */
public class EmployeePaginationInfo {
     
    private int noOfPages;
    private List<EmployeeInfo> employees;   
    
    /**
     * Getter and Setter function 
     * for all variables are written seperately 
     */
    
    public void setNoOfPages(int noOfPages) {
        this.noOfPages = noOfPages;
    }
    
    public int getNoOfPages() {
        return noOfPages;
    }
    
    public void setEmployees(List<EmployeeInfo> employees) {
        this.employees = employees;
    }
    
    public List<EmployeeInfo> getEmployees() {
        return employees;
    }
}
