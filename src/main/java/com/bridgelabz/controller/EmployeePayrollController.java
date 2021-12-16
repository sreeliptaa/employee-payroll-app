package com.bridgelabz.controller;

import com.bridgelabz.dto.EmployeeDto;
import com.bridgelabz.service.EmployeePayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    private EmployeePayrollService employeePayrollService;

    /**
     * Purpose : Method to get all the employee data from employee payroll service.
     *
     * @return : Returns list of employee detail in JSON format.
     */
    @GetMapping(value = "/detail")
    public List<EmployeeDto> getListOfAllEmployee() {

        return employeePayrollService.getListOfAllEmployee();
    }

    /**
     * Purpose : Method to add new employee data in employee payroll service
     *
     * @return : Returns data if its added.
     */
    @PostMapping(value = "/detail")
    public String addEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        return employeePayrollService.addEmployee(employeeDto);
    }

    /**
     * Purpose : Method to update employee data from employee payroll service by id
     *
     * @return : the updated detail of employee service.
     */
    @PutMapping("/detail/{id}")
    public String updateEmployee(@PathVariable(value = "id") int id,
                                 @Valid @RequestBody EmployeeDto employeeDto){
        return employeePayrollService.updateEmployee(id, employeeDto);
    }

    /**
     * Purpose : Method to delete employee data from employee payroll service by id
     *
     * @return : the deleted status of employee service.
     */
    @DeleteMapping("/detail/{id}")
    public String deleteEmployee(@PathVariable int id) {

        return employeePayrollService.deleteEmployee(id);
    }

}


