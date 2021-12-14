package com.bridgelabz.controller;

import com.bridgelabz.dto.EmployeeDto;
import com.bridgelabz.dto.ResponseDto;
import com.bridgelabz.entity.EmployeeEntity;
import com.bridgelabz.service.EmployeePayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Purpose : To demonstrate various HTTP request methods
 *
 * @author : SREELIPTA
 * @since : 06-12-2021
 */
@RestController
@RequestMapping(value = "/employee")
public class EmployeePayrollController {

    @Autowired
    EmployeePayrollService employeePayrollService;

    /**
     * Purpose : Method to add new employee data in employee payroll service
     *
     * @return : Returns data if its added.
     */
    @PostMapping(value = "/data")
    public ResponseEntity<ResponseDto> addEmployeePayrollData(
            @RequestBody EmployeeDto employeePayrollDto) {
        EmployeeEntity employeeEntity = employeePayrollService.addEmployeeData(employeePayrollDto);
        ResponseDto responseDto = new ResponseDto("Employee Payroll Data Added ", employeeEntity);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * Purpose : Method to get all the employee data from employee payroll service.
     *
     * @return : Returns list of employee detail in JSON format.
     */
    @GetMapping(value = "/data")
    public ResponseEntity<ResponseDto> getAllEmployeeData() {
        List<EmployeeEntity> employeeEntity = employeePayrollService.getAllEmployeeData();
        ResponseDto responseDto = new ResponseDto("List of Employee Data", employeeEntity);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * Purpose : Method to get the particular employee data from employee payroll service.
     *
     * @return : Return the employee detail in JSON format.
     */
    @GetMapping(value = "/data/{empId}")
    public ResponseEntity<ResponseDto> getAllEmployeeData(
            @PathVariable int empId) {
        EmployeeEntity employeeEntity = employeePayrollService.findEmployeeById(empId);
        ResponseDto responseDto = new ResponseDto("Employee Data For this Id", employeeEntity);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    /**
     * Purpose : Method to update employee data from employee payroll service by id
     *
     * @return : the updated detail of employee service.
     */
    @PutMapping(value = "/data/{empId}")
    public ResponseEntity<ResponseDto> updateEmployeeData(
            @PathVariable int empId,
            @RequestBody EmployeeDto employeeDto) {
        String employeeEntity = employeePayrollService.updateEmployeeData(empId, employeeDto);
        ResponseDto responseDto = new ResponseDto("Employee Payroll Data Updated ", employeeEntity);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * Purpose : Method to delete employee data from employee payroll service by id
     *
     * @return : the deleted status of employee service.
     */
    @DeleteMapping(value = "/data/{empId}")
    public ResponseEntity<ResponseDto> deleteEmployeeData(
            @PathVariable int empId) {
        employeePayrollService.deleteEmployeeData(empId);
        ResponseDto responseDto = new ResponseDto(" Employee Payroll Data Deleted ", "deleted id: " + empId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}


