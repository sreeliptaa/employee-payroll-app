package com.bridgelabz.service;

import com.bridgelabz.builder.EmployeePayrollBuilder;
import com.bridgelabz.dto.EmployeeDto;
import com.bridgelabz.entity.EmployeeEntity;
import com.bridgelabz.exception.CustomException;
import com.bridgelabz.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EmployeePayrollServiceTest {

    @InjectMocks
    private EmployeePayrollService employeePayrollService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private EmployeePayrollBuilder employeePayrollBuilder;

    @Test
    void givenwhenGetAllEmployeeDataCalled_ShouldReturnTheListOfEmployee() {
        List<EmployeeEntity> employeeEntityList = new ArrayList<>();
        EmployeeEntity employeeEntity1 = new EmployeeEntity();
        employeeEntity1.setId(1);
        employeeEntity1.setName("Sreelipta");
        employeeEntity1.setSalary(20000);
        employeeEntity1.setGender("Female");
        employeeEntity1.setDepartment("cse");
        employeeEntity1.setNotes("Regular");

        employeeEntityList.add(employeeEntity1);
        EmployeeEntity employeeEntity2 = new EmployeeEntity();
        employeeEntity2.setId(2);
        employeeEntity2.setName("Simran");
        employeeEntity2.setSalary(25000);
        employeeEntity2.setGender("Female");
        employeeEntity2.setDepartment("cse");
        employeeEntity2.setNotes("Regular");

        employeeEntityList.add(employeeEntity2);

        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        EmployeeDto employeeDto1 = new EmployeeDto();
        employeeDto1.setName("Sreelipta");
        employeeDto1.setSalary(20000);
        employeeDto1.setGender("Female");
        employeeDto1.setDepartment("cse");
        employeeDto1.setNotes("Regular");
        employeeDtoList.add(employeeDto1);
        EmployeeDto employeeDto2 = new EmployeeDto();
        employeeDto2.setName("Simran");
        employeeDto2.setSalary(25000);
        employeeDto2.setGender("Female");
        employeeDto2.setDepartment("cse");
        employeeDto2.setNotes("Regular");
        employeeDtoList.add(employeeDto2);

        when(employeeRepository.findAll()).thenReturn(employeeEntityList);
        when(modelMapper.map(employeeEntityList.get(0), EmployeeDto.class)).thenReturn(employeeDto1);
        when(modelMapper.map(employeeEntityList.get(1), EmployeeDto.class)).thenReturn(employeeDto2);
        List<EmployeeDto> actualListOfEmployee = employeePayrollService.getListOfAllEmployee();
        assertEquals(2, actualListOfEmployee.size());
        assertEquals(employeeDtoList, actualListOfEmployee);
    }

    @Test
    void WhenFindEmployeeDetailsByIdCalled_ThenIfIdIsNotFound_ShouldThrowException() {
        int id = 1;
        when(employeeRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(CustomException.class, () -> employeePayrollService.findAtmEntityById(id));
    }

    @Test
    void givenwhenAddEmployeeDataCalled_ShouldAddEmployeeDataAndReturnSuccessMessage() {
        String successMessage = "Employee added successfully";
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Sreelipta");
        employeeDto.setSalary(20000);
        employeeDto.setGender("Female");
        employeeDto.setDepartment("cse");
        employeeDto.setNotes("Regular");

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(1);
        employeeEntity.setName("Sreelipta");
        employeeEntity.setSalary(20000);
        employeeEntity.setGender("Female");
        employeeEntity.setDepartment("cse");
        employeeEntity.setNotes("Regular");

        when(modelMapper.map(employeeDto, EmployeeEntity.class)).thenReturn(employeeEntity);
        String actualMessage = employeePayrollService.addEmployee(employeeDto);
        assertEquals(successMessage, actualMessage);
        verify(employeeRepository, times(1)).save(employeeEntity);
    }

    @Test
    void givenwhenUpdateEmployeeDataCalled_ShouldUpdateEmployeeDataAndReturnSuccessMessage() {
        int employeeId = 1;
        ArgumentCaptor<EmployeeEntity> employeePayrollArgumentCaptor = ArgumentCaptor.forClass(EmployeeEntity.class);
        String successMessage = "Employee Details updated successfully";
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Sreelipta");
        employeeDto.setSalary(20000);
        employeeDto.setGender("Female");
        employeeDto.setDepartment("cse");
        employeeDto.setNotes("Regular");

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(1);
        employeeEntity.setName("Sreelipta");
        employeeEntity.setSalary(20000);
        employeeEntity.setGender("Female");
        employeeEntity.setDepartment("cse");
        employeeEntity.setNotes("Regular");

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employeeEntity));
        when(employeePayrollBuilder.buildEmployeeEntity(employeeDto, employeeEntity)).thenReturn(employeeEntity);
        String actualMessage = employeePayrollService.updateEmployee(employeeId, employeeDto);
        assertEquals(successMessage, actualMessage);
        verify(employeeRepository, times(1)).save(employeePayrollArgumentCaptor.capture());
        assertEquals(employeeEntity.getName(), employeePayrollArgumentCaptor.getValue().getName());
        assertEquals(employeeEntity.getSalary(), employeePayrollArgumentCaptor.getValue().getSalary());
        assertEquals(employeeEntity.getGender(), employeePayrollArgumentCaptor.getValue().getGender());
        assertEquals(employeeEntity.getDepartment(), employeePayrollArgumentCaptor.getValue().getDepartment());
        assertEquals(employeeEntity.getNotes(), employeePayrollArgumentCaptor.getValue().getNotes());

    }

    @Test
    void givenAEmployeeDetails_whenUpdateEmployeeIsCalled_shouldThrowExceptionMessage() {
        int employeeId = 1;
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Sreelipta");
        employeeDto.setSalary(20000);
        employeeDto.setGender("Female");
        employeeDto.setDepartment("cse");
        employeeDto.setNotes("Regular");
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());
        assertThrows(CustomException.class, () -> employeePayrollService.updateEmployee(employeeId, employeeDto));
    }

    @Test
    void givenwhenDeleteEmployeeDataCalled_ShouldDeleteEmployeeDataAndReturnSuccessMessage() {
        String successMessage = "Employee details deleted successfully";
        int employeeId = 1;
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Sreelipta");
        employeeDto.setSalary(20000);
        employeeDto.setGender("Female");
        employeeDto.setDepartment("cse");
        employeeDto.setNotes("Regular");

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(1);
        employeeEntity.setName("Sreelipta");
        employeeEntity.setSalary(20000);
        employeeEntity.setGender("Female");
        employeeEntity.setDepartment("cse");
        employeeEntity.setNotes("Regular");

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employeeEntity));
        String actualMessage = employeePayrollService.deleteEmployee(employeeId);
        assertEquals(successMessage, actualMessage);
        verify(employeeRepository, times(1)).delete(employeeEntity);
    }


}
