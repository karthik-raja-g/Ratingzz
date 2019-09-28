package com.ideas2it.ratingsystem.service;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2it.ratingsystem.constant.Constant;
import com.ideas2it.ratingsystem.dao.EmployeeDao;
import com.ideas2it.ratingsystem.model.Employee;
import com.ideas2it.ratingsystem.model.Role;
import com.ideas2it.ratingsystem.info.EmployeeInfo;
import com.ideas2it.ratingsystem.info.EmployeePaginationInfo;
import com.ideas2it.ratingsystem.util.CommonUtil;
import com.ideas2it.ratingsystem.util.DateUtil;
import com.ideas2it.ratingsystem.service.RoleService;

/**
 * The interface for service layer for employee, 
 * which helps in creating implementations.
 */
public interface EmployeeService {
    
    /**
     * This method access DB, counts total number of entries and returns
     * number of entries present in table.
     * 
     * @return - count of entries in employee table.
     */
    int totalIdInTable();
    
    /**
     * Gets entries of user as object.
     * Inserts employee and gets Id , then save picture with this id in name.
     * Saves picture path to object and updates.
     *
     * @param roleId - Selected role id for the employee.
     * @param employeeInfo - a info object contains employee informations
     * @return employeeInfo - created employeeInfo object with id 
     * @throws IOException - will be thrown when intreption occured 
     *                          during file I/O operation.
     */
    public EmployeeInfo createEmployee(EmployeeInfo employeeInfo,int roleId) 
            throws IOException;
            
    public List<EmployeeInfo> getReportingPerson(String roleName);
    
    /**
     * Gets employee id as input, fetches employee for corresponding id
     * and returns it.
     *
     * @param id - id of the employee to be fetched.
     * @return employeeInfo - employee as info object to controller.
     */
    EmployeeInfo fetchEmployeeInfo(int id);
      
    /**
     * Gets updated employeeInfo and sets new informations 
     * into database fetched object and updates to db
     * 
     * @param file - profile picture of the employee
     * @param employeeInfo - Info object contains employee updated informations
     * @return employeeInfo - Updated employeeInfo object
     * @throws IOException - will be thrown when intreption occured 
     *                          during file I/O operation.
     */
    EmployeeInfo updateEmployee(EmployeeInfo employeeInfo) 
            throws IOException;
    
    /**
     * Fetches employees by offset and limit, sets to info and returns
     *
     * @param offset - The starting id from which employee to be fetched.
     * @return employeePaginationInfo - Info object with pagination data
     */
    EmployeePaginationInfo fetchEmployees(int offset);
    
    /**
     * Soft delete for an employee
     *
     * @param employeeId - Id of the employee to be soft deleted.
     * @return boolean - returns true when employee soft delete done
     *                  else returns false.
     */
    boolean deleteEmployee(int employeeId);
    
    /**
     * Fetches employees in limit, and sets them 
     * into JSON object and collect them in JSONArray to return to jsp page.
     *
     * @param page - page number which indirectly represents offset.
     * @return array - JSONArray containing employee details 
     */
    List<EmployeeInfo> fetchEmployeesByPage(int page); 
    
    /**
     * It returns a employee based on reporting person
     *
     * @param employee - The reporting person object of employees needed
     * @return employees - The list of employees reporting to 
     *       If no employee is found, the value
     *                               will be null.
     */
    List<Employee> fetchSubordinates();
    
   /**
     * It returns a employee based on username
     *
     * @param username - The username of employee needed
     * @return employee - The particular employee for the username.
     *       If no employee is found, the value
     *                               will be null.
     */
    Employee fetchEmployeeByUsername(String username);
    
    /**
     * It returns a employee based on role
     *
     * @param role - The role of employees needed
     * @return employees - The list of employees in that role.
     *       If no employee is found, the value
     *                               will be null.
     */    
    List<Employee> fetchEmployeesByRole(Role role);
    
    /**
     * It returns a employee corresponding to given id
     *
     * @param id - The id of employee to be fetched
     * @return employee - The particular employee for the id.
     *       If no employee is found, the value
     *                               will be null.
     */    
    Employee getEmployeeById(int Id);
    
    /**
     * Gets created employeeId and to whom he/she will be reporting their id
     * fetches both and create relationship and updates the employee.
     *
     * @param employeeId - Created employee id.
     * @param reportingPersonId - to whom he/she going to report.
     * @return EmployeeInfo - Created employee with reporting person.
     */
    EmployeeInfo addReportingPersonForEmployee(
            int employeeId, int reportingPersonId);
            
    EmployeeInfo getEmployeeProfile();
}

