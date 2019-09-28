package com.ideas2it.ratingsystem.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session; 
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ideas2it.ratingsystem.model.Employee;
import com.ideas2it.ratingsystem.model.Role;

/**
 *<p>
 * It adds,deletes,updates Employees from database. 
 * It can retrive Employee information from the database
 *</p>
 *
 * @author  J.Balakumaran   created on 5 Sep 2019
 *
 */
public interface EmployeeDao {

     /**
     * It adds a employee to database 
     * 
     * @return size - Number of active employees in db.
     */
    int totalIdInTable();
    
    /**
     * It adds a employee to database.
     *
     * @param employee - The employee to be added to database without id.
     * @return employee - Created employee with id.
     */
    Employee insertEmployee(Employee employee);

    /**
     * It returns a list of all employee in database 
     *
     * @param offset - from which count the fetch should start in db.
     * @return employees - The list with all employees.
     *      If no employees are found, the value of list will be null.
     */
    List<Employee> retriveEmployees(int offSet);

    /**
     * It saves the updated employee into the database again
     *
     * @param employee - The updated employee object.
     * @return status - returns boolean status of update , true or false.
     */
    boolean updateEmployee(Employee employee);

    /**
     * It returns a employee based on Employee Id
     *
     * @param employeeId - The employee Id of employee needed
     * @return employee - The particular employee for the employee Id.
     *       If no employee is found, the value
     *                               will be null.
     */
    Employee retriveEmployeeById(int employeeId);
    
    /**
     * It returns a employee based on reporting person
     *
     * @param employee - The reporting person object of employees needed
     * @return employees - The list of employees reporting to 
     *       If no employee is found, the value
     *                               will be null.
     */
    List<Employee> retriveSubordinates(Employee employee);
    
    /**
     * It returns a employee based on username
     *
     * @param username - The username of employee needed
     * @return employee - The particular employee for the username.
     *       If no employee is found, the value
     *                               will be null.
     */
    Employee retriveEmployeeByUsername(String username);
    
    /**
     * It returns a employee based on role
     *
     * @param role - The role of employees needed
     * @return employees - The list of employees in that role.
     *       If no employee is found, the value
     *                               will be null.
     */
    List<Employee> retriveEmployeesByRole(Role role);
    
    /**
     * Retrive all active employee present in db.
     *
     * @return employees - List of all active employees.
     */
    List<Employee> getAllEmployees();
}
