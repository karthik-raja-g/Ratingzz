package com.ideas2it.ratingsystem.util; 

import java.util.List;
import java.util.ArrayList;

import com.ideas2it.ratingsystem.model.Employee;
import com.ideas2it.ratingsystem.info.EmployeeInfo;

public class CommonUtil {
  
    /**
     * Employee pojo object is converted into EmployeeInfo by fetching from 
     * Employee and setting them into EmployeeInfo.
     * 
     * @param Employee - entity object which to be converted to Info
     * @return EmployeeInfo - converted info object 
     */
    public static EmployeeInfo convertEmployeeModelToEmployeeInfo(
            Employee employee){
        EmployeeInfo employeeInfo = new EmployeeInfo();
        employeeInfo.setId(employee.getId());
        if(null != employee.getName()) {
            employeeInfo.setName(employee.getName());
        }
        if(null != employee.getUsername()) {
            employeeInfo.setUsername(employee.getUsername());
        }
        if(null != employee.getPassword()) {
            employeeInfo.setPassword(employee.getPassword());
        }
        if(null != employee.getEmail()) {
            employeeInfo.setEmail(employee.getEmail());
        }
        if(null != employee.getModifiedDate()) {
            employeeInfo.setModifiedDate(employee.getModifiedDate());
        }
        if(null != employee.getProfilePicUrl()) {
            employeeInfo.setProfilePicUrl(employee.getProfilePicUrl());
        }
        if(null != employee.getRole()) {
            employeeInfo.setRole((employee.getRole()).getName());
        }
        if(null != employee.getReportingPerson()) {
            employeeInfo.setReportingPerson((
                    employee.getReportingPerson()).getName());
        }
        if(null != employee.getCreatedDate()) {
            employeeInfo.setCreatedDate(employee.getCreatedDate());
        }
        return employeeInfo;
    }
    
    /**
     * EmployeeInfo is converted into Employee pojo object by fetching from 
     * EmployeeInfo and setting them into Employee
     * 
     * @param EmployeeInfo - converted info object
     * @return Employee - entity object which will be converted to Info
     */
    public static Employee convertEmployeeInfoToEmployeeModel(
            EmployeeInfo employeeInfo) {
        Employee employee = new Employee();
        employee.setId(employeeInfo.getId());
        if(null != employeeInfo.getName()) {
            employee.setName(employeeInfo.getName());
        }
        if(null != employeeInfo.getUsername()) {
            employee.setUsername(employeeInfo.getUsername());
        }
        if(null != employeeInfo.getPassword()) {
            employee.setPassword(employeeInfo.getPassword());
        }
        if(null != employeeInfo.getEmail()) {
            employee.setEmail(employeeInfo.getEmail());
        }
        if(null != employeeInfo.getModifiedDate()) {
            employee.setModifiedDate(employeeInfo.getModifiedDate());
        }
        if(null != employeeInfo.getProfilePicUrl()) {
            employee.setProfilePicUrl(employeeInfo.getProfilePicUrl());
        }
        return employee;
    }  
    
    /**
     * List of Employee pojo objects are converted into
     * List of EmployeeInfo by calling convertPlayerEntityToPlayerInfo method
     * 
     * @param employees - List of entity object which to be converted to Info
     * @return employeesInfo - converted List of info object 
     */
    public static List<EmployeeInfo> convertEmployeesToEmployeesInfo(
            List<Employee> employees) {
        List<EmployeeInfo> employeesInfo = new ArrayList<EmployeeInfo>();
        for(Employee employee : employees) {
            employeesInfo.add(convertEmployeeModelToEmployeeInfo(employee));
        }
        return employeesInfo;
    }
}
