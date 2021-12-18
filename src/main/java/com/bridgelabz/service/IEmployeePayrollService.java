package com.bridgelabz.service;

import com.bridgelabz.dto.EmployeeDto;
import com.bridgelabz.entity.EmployeeEntity;
import java.util.List;

/**
 * Purpose : This is the interface of employee payroll service by which all the process of service class will occur
 *
 * @author SREELIPTA
 * @since 2021-12-11
 */
public interface IEmployeePayrollService {

    List<EmployeeDto> getListOfAllEmployee();

    EmployeeEntity findEmployeeById(int id);

    String addEmployee(EmployeeDto employeeDto);

    String updateEmployee(int id, EmployeeDto employeeDto);

    String deleteEmployee(int id);
}
