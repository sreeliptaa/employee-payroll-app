package com.bridgelabz.service;

import com.bridgelabz.builder.EmployeePayrollBuilder;
import com.bridgelabz.dto.EmployeeDto;
import com.bridgelabz.entity.EmployeeEntity;
import com.bridgelabz.exception.EmployeePayrollCustomException;
import com.bridgelabz.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Purpose : To implement all the methods in controller class
 *
 * @author : SREELIPTA
 * @since : 06-12-2021
 */

@Service
public class EmployeePayrollService implements IEmployeePayrollService {

    private static final String EMPLOYEE_ADDED_SUCCESSFULLY = "Employee details added successfully";
    private static final String INVALID_ID = "Invalid Employee Id";
    private static final String EMPLOYEE_DELETED_SUCCESSFULLY = "Employee details deleted successfully";
    private static final String EMPLOYEE_UPDATED_SUCCESSFULLY = "Employee Details updated successfully";

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmployeePayrollBuilder employeePayrollBuilder;

    /**
     * Purpose : To get list of all the record of employee payroll service
     *
     * @return the list of all employee records
     */
    @Override
    public List<EmployeeDto> getListOfAllEmployee() {
        return employeeRepository
                .findAll()
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    /**
     * Purpose : To get particular record of employee payroll service by id
     *
     * @param id unique id of the records
     * @return the status of the employee records
     */
    @Override
    public EmployeeEntity findEmployeeById(int id) {
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeePayrollCustomException(INVALID_ID));
    }

    /**
     * Purpose : To add the employee records to the database
     *
     * @param employeeDto :is used to add data from client
     * @return employee payroll records are created
     */
    @Override
    public String addEmployee(EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity = modelMapper.map(employeeDto, EmployeeEntity.class);
        employeeRepository.save(employeeEntity);
        return EMPLOYEE_ADDED_SUCCESSFULLY;
    }

    /**
     * Purpose : To update the available records in the database
     *
     * @param id          unique id of the records
     * @param employeeDto getting data from client
     * @return updated records of the employee payroll service
     */
    @Override
    public String updateEmployee(int id, EmployeeDto employeeDto) throws EmployeePayrollCustomException {
        EmployeeEntity employeeEntity = findEmployeeById(id);
        employeeEntity = employeePayrollBuilder.buildEmployeeEntity(employeeDto, employeeEntity);
        employeeRepository.save(employeeEntity);
        return EMPLOYEE_UPDATED_SUCCESSFULLY;
    }

    /**
     * Purpose : To delete particular record from the employee payroll service
     *
     * @param id unique id of the employee payroll service records
     * @return the status of the record which deleted or not
     */
    @Override
    public String deleteEmployee(int id) throws EmployeePayrollCustomException {
        EmployeeEntity employeeEntity = findEmployeeById(id);
        employeeRepository.delete(employeeEntity);
        return EMPLOYEE_DELETED_SUCCESSFULLY;
    }

}