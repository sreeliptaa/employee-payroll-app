package com.bridgelabz.service;

import com.bridgelabz.builder.EmployeePayrollBuilder;
import com.bridgelabz.dto.EmployeeDto;
import com.bridgelabz.entity.EmployeeEntity;
import com.bridgelabz.exception.CustomException;
import com.bridgelabz.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Purpose : To implement all the methods in controller class
 *
 * @author : SREELIPTA
 * @since : 06-12-2021
 */

@Service
public class EmployeePayrollService implements IEmployeePayrollService {

    private static final String EMPLOYEE_DETAIL_UPDATED_SUCCESSFULLY = "Employee data Updated Successfully";
    private static final String EMPLOYEE_DETAIL_DELETED_SUCCESSFULLY = "Employee data Deleted Successfully";


    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeePayrollBuilder employeePayrollBuilder;

    /**
     * Purpose : To add the employee records to the database
     *
     * @param employeeDto :is used to add data from client
     * @return employee payroll records are created
     */
    @Override
    public EmployeeEntity addEmployeeData(EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity = employeePayrollBuilder.buildEmployeeEntity(employeeDto, employeeEntity);
        return employeeRepository.save(employeeEntity);
    }

    /**
     * Purpose : To get list of all the record of employee payroll service
     *
     * @return the list of all employee records
     */
    @Override
    public List<EmployeeEntity> getAllEmployeeData() {
        return employeeRepository.findAll();
    }

    /**
     * Purpose : To get particular record of employee payroll service by id
     *
     * @param employeeId unique id of the records
     * @return the status of the employee records
     */
    @Override
    public EmployeeEntity findEmployeeById(int employeeId) {
        return employeeRepository.findById(employeeId).
                orElseThrow(() -> new CustomException("Employee data not found of this id :" + employeeId));
    }

    /**
     * Purpose : To update the available records in the database
     *
     * @param employeeId  unique id of the records
     * @param employeeDto getting data from client
     * @return updated records of the employee payroll service
     */
    @Override
    public String updateEmployeeData(int employeeId, EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity = findEmployeeById(employeeId);
        employeeEntity = employeePayrollBuilder.buildEmployeeEntity(employeeDto, employeeEntity);
        employeeRepository.save(employeeEntity);
        return EMPLOYEE_DETAIL_UPDATED_SUCCESSFULLY;
    }

    /**
     * Purpose : To delete particular record from the employee payroll service
     *
     * @param employeeId unique id of the employee payroll service records
     * @return the status of the record which deleted or not
     */
    @Override
    public String deleteEmployeeData(int employeeId) {
        EmployeeEntity employeeEntity = findEmployeeById(employeeId);
        employeeRepository.deleteById(employeeId);
        return EMPLOYEE_DETAIL_DELETED_SUCCESSFULLY;
    }
}