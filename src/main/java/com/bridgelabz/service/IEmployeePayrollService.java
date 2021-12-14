package com.bridgelabz.service;

import com.bridgelabz.dto.EmployeeDto;
import com.bridgelabz.entity.EmployeeEntity;

import java.util.List;

/**
 * Purpose : To occure all the process of employee payroll service class
 *
 * @author SREELIPTA
 * @since 06-12-2021
 */
public interface IEmployeePayrollService {
    List<EmployeeEntity> getAllEmployeeData();

    EmployeeEntity addEmployeeData(EmployeeDto employeeDto);

    EmployeeEntity findEmployeeById(int empId);

    String updateEmployeeData(int empId, EmployeeDto employeeDto);

    String deleteEmployeeData(int empId);
}
