package com.ideas2it.ratingsystem.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.net.URL;

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
import com.ideas2it.ratingsystem.util.SessionEmployeeProvider;
import com.ideas2it.ratingsystem.service.EmployeeService;
import com.ideas2it.ratingsystem.service.RoleService;

/**
 * The class which contains all business logics and calculations,
 * process done for player informations.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    
    private static final String ROLE_EMPLOYEE = "ROLE_EMPLOYEE";
    private static final String ROLE_TEAM_LEADER = "ROLE_TEAM_LEADER";
    private static final String ROLE_MANAGER = "ROLE_MANAGER";
 
    @Autowired
    private EmployeeDao employeeDao;
    
    @Autowired
    private RoleService roleService;
    
    /**
     * {@inheritdoc}
     */
    @Override
    public int totalIdInTable() {
        return employeeDao.totalIdInTable();
    }
    
    /**
     * {@inheritdoc}
     */
    @Override
    public EmployeeInfo createEmployee(EmployeeInfo employeeInfo,int roleId) 
            throws IOException {
        Employee employee = 
                CommonUtil.convertEmployeeInfoToEmployeeModel(employeeInfo);
        System.out.println(employee);                
        Role role = roleService.getRoleById(roleId);
        employee.setRole(role);
        employee.setCreatedDate(DateUtil.getDate());
        employee.setModifiedDate(DateUtil.getDate());
        Employee savedEmployee = employeeDao.insertEmployee(employee);
        return CommonUtil.convertEmployeeModelToEmployeeInfo(savedEmployee);
    } 
    
    /**
     * Get role name as input and fetches their 
     * higher authority persons and returns
     *
     * @param roleName - Desigination of the role for 
     *      which higher authority persons to be fetched.
     */
    @Override
    public List<EmployeeInfo> getReportingPerson(String roleName) {
        List<Employee> reportingPersons = new ArrayList<Employee>();
        List<Employee> employees = employeeDao.getAllEmployees();
        for (Employee employee : employees) {
            Role role = employee.getRole();
            if (roleName.equals(ROLE_EMPLOYEE) && role.getName().equals(ROLE_TEAM_LEADER)) {
                reportingPersons.add(employee);
            } else if (roleName.equals(ROLE_TEAM_LEADER) && role.getName().equals(ROLE_MANAGER)) {
                reportingPersons.add(employee);
            }
        }
        return CommonUtil.convertEmployeesToEmployeesInfo(reportingPersons);            
    }
    
    /**
     * {@inheritdoc}
     */
    @Override
    public EmployeeInfo fetchEmployeeInfo(int id) {
        Employee employee = employeeDao.retriveEmployeeById(id);
        return CommonUtil.convertEmployeeModelToEmployeeInfo(employee);
    }
    
    /**
     * {@inheritdoc}
     */  
    @Override
    public EmployeeInfo updateEmployee(EmployeeInfo employeeInfo) 
            throws IOException {  
        Employee updatedEmployee = 
                CommonUtil.convertEmployeeInfoToEmployeeModel(employeeInfo);
        Employee employee = employeeDao.retriveEmployeeById(updatedEmployee.getId());
        employee.setName(updatedEmployee.getName());
        employee.setUsername(updatedEmployee.getUsername());        
        employee.setEmail(updatedEmployee.getEmail());
        employee.setModifiedDate(DateUtil.getDate());
        employeeDao.updateEmployee(employee);
        return CommonUtil.convertEmployeeModelToEmployeeInfo(employee);
    }
    
    /**
     * {@inheritdoc}
     */
    @Override
    public EmployeePaginationInfo fetchEmployees(int offset) { 
        EmployeePaginationInfo employeePaginationInfo = 
                new EmployeePaginationInfo();
        int noOfPages = (int) Math.ceil(totalIdInTable() * 1.0 / 
                            Constant.NO_OF_RECORDS_PER_PAGE);
        List<Employee> employees = employeeDao.retriveEmployees(offset);
        employeePaginationInfo.setEmployees(
                CommonUtil.convertEmployeesToEmployeesInfo(employees));
        employeePaginationInfo.setNoOfPages(noOfPages);
        return employeePaginationInfo;
    }
    
    /**
     * {@inheritdoc}
     */
    @Override
    public boolean deleteEmployee(int employeeId) {
        boolean status = Boolean.FALSE;
        Employee employee = employeeDao.retriveEmployeeById(employeeId);
        employee.setIsActive(Boolean.FALSE);
        if(employeeDao.updateEmployee(employee)) {
            status = Boolean.TRUE;
        }
        return status;
    }
    
    /**
     * {@inheritdoc}
     */
    @Override
    public List<EmployeeInfo> fetchEmployeesByPage(int page) {
        EmployeePaginationInfo employeePaginationInfo = 
                fetchEmployees((page - 1) * Constant.NO_OF_RECORDS_PER_PAGE);
        List<EmployeeInfo> employees = employeePaginationInfo.getEmployees();
        return employees;
    } 
    
    /**
     * {@inheritdoc}
     */
    @Override
    public List<Employee> fetchSubordinates() {
        Employee employee = SessionEmployeeProvider.getSessionEmployee();
        return employeeDao.retriveSubordinates(employee);
    }
    
    /**
     * {@inheritdoc}
     */
    @Override
    public Employee fetchEmployeeByUsername(String username) {
        return employeeDao.retriveEmployeeByUsername(username);
    }
    
    /**
     * {@inheritdoc}
     */
    @Override
    public List<Employee> fetchEmployeesByRole(Role role) {
        return employeeDao.retriveEmployeesByRole(role);
    }
    
    /**
     * {@inheritdoc}
     */   
    @Override
    public Employee getEmployeeById(int id) {
        return employeeDao.retriveEmployeeById(id);
    }
    
    /**
     * {@inheritdoc}
     */
    @Override
    public EmployeeInfo addReportingPersonForEmployee(
            int employeeId, int reportingPersonId) {
        Employee employee = employeeDao.retriveEmployeeById(employeeId);
        System.out.println(employeeDao.retriveEmployeeById(reportingPersonId));
        employee.setReportingPerson(
                employeeDao.retriveEmployeeById(reportingPersonId));
        employeeDao.updateEmployee(employee);
        return CommonUtil.convertEmployeeModelToEmployeeInfo(employee);
    }
    
    @Override
    public EmployeeInfo getEmployeeProfile() {
        Employee employee = SessionEmployeeProvider.getSessionEmployee();
        return CommonUtil.convertEmployeeModelToEmployeeInfo(employee);
    }
}
