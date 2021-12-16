package com.bridgelabz.controller;

import com.bridgelabz.dto.EmployeeDto;
import com.bridgelabz.service.EmployeePayrollService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class EmployeePayrollControllerTest {

    @InjectMocks
    private EmployeePayrollController employeePayrollController;
    @Mock
    private EmployeePayrollService employeePayrollService;

    @Test
    void givenEmployeeDetail_WhenGetEmployeeDetail_shouldReturnListOfEmployee() {
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Sreelipta");
        employeeDto.setGender("Female");
        employeeDto.setSalary(20000);
        employeeDto.setDepartment("cse");
        employeeDto.setNotes("Regular");
        employeeDto.setJoiningDate("15/01/2021");
        employeeDtoList.add(employeeDto);
        EmployeeDto employeeDto1 = new EmployeeDto();
        employeeDto1.setName("Simran");
        employeeDto1.setSalary(25000);
        employeeDto1.setGender("Female");
        employeeDto1.setDepartment("cse");
        employeeDto1.setNotes("Regular");
        employeeDto1.setJoiningDate("05/06/2021");
        employeeDtoList.add(employeeDto1);
        when(employeePayrollService.getListOfAllEmployee()).thenReturn(employeeDtoList);
        List<EmployeeDto> actualResponse = employeePayrollController.getListOfAllEmployee();
        for (int i = 0; i < actualResponse.size(); i++) {
            assertEquals(employeeDtoList.get(i).getName(), actualResponse.get(i).getName());
            assertEquals(employeeDtoList.get(i).getSalary(), actualResponse.get(i).getSalary());
            assertEquals(employeeDtoList.get(i).getGender(), actualResponse.get(i).getGender());
            assertEquals(employeeDtoList.get(i).getDepartment(), actualResponse.get(i).getDepartment());
            assertEquals(employeeDtoList.get(i).getNotes(), actualResponse.get(i).getNotes());
        }
    }

    @Test
    void givenAEmployeeDetail_whenAddEmployeeDetailCalled_shouldAddTheEmployee() {
        String successString = "Employee added successfully";
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Sreelipta");
        employeeDto.setSalary(20000);
        employeeDto.setGender("Female");
        employeeDto.setDepartment("cse");
        employeeDto.setNotes("Regular");
        employeeDto.setJoiningDate("15/01/2021");
        when(employeePayrollService.addEmployee(employeeDto)).thenReturn(successString);
        String actualResponseString = employeePayrollController.addEmployee(employeeDto);
        assertEquals(successString, actualResponseString);
    }

    @Test
    void givenAEmployeeDetail_whenUpdateEmployeeDetailCalled_shouldUpdateTheEmployee() {
        String successString = "Employee Details updated successfully";
        int employeeId = 1;
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Sreelipta");
        employeeDto.setSalary(20000);
        employeeDto.setGender("Female");
        employeeDto.setDepartment("cse");
        employeeDto.setNotes("Regular");
        employeeDto.setJoiningDate("15/01/2021");
        when(employeePayrollService.updateEmployee(employeeId, employeeDto)).thenReturn(successString);
        String actualResponseString = employeePayrollController.updateEmployee(employeeId,employeeDto);
        assertEquals(successString, actualResponseString);
    }

    @Test
    void givenAEmployeeId_whenDeleteEmployeeDataCalled_shouldDeleteTheEmployee() {
        String successString = "Employee details deleted successfully";
        int employeeId = 1;
        when(employeePayrollService.deleteEmployee(employeeId)).thenReturn(successString);
        String actualResponseString = employeePayrollController.deleteEmployee(employeeId);
        assertEquals(successString, actualResponseString);
    }
}
