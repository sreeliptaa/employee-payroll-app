package com.bridgelabz.service;

import com.bridgelabz.builder.EmployeePayrollBuilder;
import com.bridgelabz.dto.EmployeeDto;
import com.bridgelabz.entity.EmployeeEntity;
import com.bridgelabz.exception.EmployeePayrollCustomException;
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

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private EmployeePayrollBuilder employeePayrollBuilder;

    @InjectMocks
    private EmployeePayrollService employeePayrollService;

    @Test
    void givenWhenGetAllEmployeeCalled_ShouldReturnTheListOfEmployee() {
        List<EmployeeEntity> employeeEntityList = new ArrayList<>();
        EmployeeEntity employeeEntity1 = new EmployeeEntity();
        employeeEntity1.setName("Sreelipta");
        employeeEntity1.setGender("Female");
        employeeEntity1.setSalary(20000);
        employeeEntity1.setDepartments((List.of("Cse")));
        employeeEntity1.setNotes("Regular");
        employeeEntity1.setJoiningDate("15/01/2021");
        employeeEntityList.add(employeeEntity1);
        EmployeeEntity employeeEntity2 = new EmployeeEntity();
        employeeEntity2.setId(2);
        employeeEntity2.setName("Simran");
        employeeEntity2.setGender("Female");
        employeeEntity2.setSalary(25000);
        employeeEntity2.setDepartments((List.of("Cse")));
        employeeEntity2.setNotes("Regular");
        employeeEntity2.setJoiningDate("05/06/2021");
        employeeEntityList.add(employeeEntity2);

        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        EmployeeDto employeeDto1 = new EmployeeDto();
        employeeDto1.setName("Sreelipta");
        employeeDto1.setGender("Female");
        employeeDto1.setSalary(20000);
        employeeDto1.setDepartments((List.of("Cse")));
        employeeDto1.setNotes("Regular");
        employeeDto1.setJoiningDate("15/01/2021");
        employeeDtoList.add(employeeDto1);
        EmployeeDto employeeDto2 = new EmployeeDto();
        employeeDto2.setName("Simran");
        employeeDto2.setSalary(25000);
        employeeDto2.setGender("Female");
        employeeDto2.setDepartments((List.of("Cse")));
        employeeDto2.setNotes("Regular");
        employeeDto2.setJoiningDate("05/06/2021");
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
        assertThrows(EmployeePayrollCustomException.class, () -> employeePayrollService.findEmployeeById(id));
    }

    @Test
    void givenWhenAddEmployeeDataCalled_ShouldAddEmployeeDataAndReturnSuccessMessage() {
        String successMessage = "Employee details added successfully";
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Sreelipta");
        employeeDto.setGender("Female");
        employeeDto.setSalary(20000);
        employeeDto.setDepartments((List.of("Cse")));
        employeeDto.setNotes("Regular");
        employeeDto.setJoiningDate("15/01/2021");

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(1);
        employeeEntity.setName("Sreelipta");
        employeeEntity.setGender("Female");
        employeeEntity.setSalary(20000);
        employeeEntity.setDepartments((List.of("Cse")));
        employeeEntity.setNotes("Regular");
        employeeEntity.setJoiningDate("15/01/2021");

        when(modelMapper.map(employeeDto, EmployeeEntity.class)).thenReturn(employeeEntity);
        String actualMessage = employeePayrollService.addEmployee(employeeDto);
        assertEquals(successMessage, actualMessage);
        verify(employeeRepository, times(1)).save(employeeEntity);
    }

    @Test
    void givenWhenUpdateEmployeeDataCalled_ShouldUpdateEmployeeDataAndReturnSuccessMessage() {
        int employeeId = 1;
        ArgumentCaptor<EmployeeEntity> employeePayrollArgumentCaptor = ArgumentCaptor.forClass(EmployeeEntity.class);
        String successMessage = "Employee Details updated successfully";
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Sreelipta");
        employeeDto.setGender("Female");
        employeeDto.setSalary(20000);
        employeeDto.setDepartments((List.of("Cse")));
        employeeDto.setNotes("Regular");
        employeeDto.setJoiningDate("15/01/2021");

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(1);
        employeeEntity.setName("Sreelipta");
        employeeEntity.setGender("Female");
        employeeEntity.setSalary(20000);
        employeeEntity.setDepartments((List.of("Cse")));
        employeeEntity.setNotes("Regular");
        employeeEntity.setJoiningDate("15/01/2021");

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employeeEntity));
        when(employeePayrollBuilder.buildEmployeeEntity(employeeDto, employeeEntity)).thenReturn(employeeEntity);
        String actualMessage = employeePayrollService.updateEmployee(employeeId, employeeDto);
        assertEquals(successMessage, actualMessage);
        verify(employeeRepository, times(1)).save(employeePayrollArgumentCaptor.capture());
        assertEquals(employeeEntity.getName(), employeePayrollArgumentCaptor.getValue().getName());
        assertEquals(employeeEntity.getSalary(), employeePayrollArgumentCaptor.getValue().getSalary());
        assertEquals(employeeEntity.getGender(), employeePayrollArgumentCaptor.getValue().getGender());
        assertEquals(employeeEntity.getDepartments(), employeePayrollArgumentCaptor.getValue().getDepartments());
        assertEquals(employeeEntity.getNotes(), employeePayrollArgumentCaptor.getValue().getNotes());
        assertEquals(employeeEntity.getJoiningDate(), employeePayrollArgumentCaptor.getValue().getJoiningDate());

    }

    @Test
    void whenUpdateEmployeeCalled_ThenIfNotFindById_ShouldThrowException() {
        int employeeId = 1;
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Sreelipta");
        employeeDto.setGender("Female");
        employeeDto.setSalary(20000);
        employeeDto.setDepartments((List.of("Cse")));
        employeeDto.setNotes("Regular");
        employeeDto.setJoiningDate("15/01/2021");
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());
        assertThrows(EmployeePayrollCustomException.class, () -> employeePayrollService.updateEmployee(employeeId, employeeDto));
    }

    @Test
    void givenWhenDeleteEmployeeCalled_ShouldDeleteEmployeeDataAndReturnSuccessMessage() {
        String successMessage = "Employee details deleted successfully";
        int employeeId = 1;
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Sreelipta");
        employeeDto.setGender("Female");
        employeeDto.setSalary(20000);
        employeeDto.setDepartments((List.of("Cse")));
        employeeDto.setNotes("Regular");
        employeeDto.setJoiningDate("15/01/2021");

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(1);
        employeeEntity.setName("Sreelipta");
        employeeEntity.setGender("Female");
        employeeEntity.setSalary(20000);
        employeeEntity.setDepartments((List.of("Cse")));
        employeeEntity.setNotes("Regular");
        employeeEntity.setJoiningDate("15/01/2021");

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employeeEntity));
        String actualMessage = employeePayrollService.deleteEmployee(employeeId);
        assertEquals(successMessage, actualMessage);
        verify(employeeRepository, times(1)).delete(employeeEntity);
    }

    @Test
    void whenDeleteEmployeeCalled_ThenIfNotFindById_ShouldThrowException() {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(1);
        employeeEntity.setName("Sreelipta");
        employeeEntity.setGender("Female");
        employeeEntity.setSalary(20000);
        employeeEntity.setDepartments((List.of("Cse")));
        employeeEntity.setNotes("Regular");
        employeeEntity.setJoiningDate("15/01/2021");
        when(employeeRepository.findById(employeeEntity.getId())).thenReturn(Optional.empty());
        assertThrows(EmployeePayrollCustomException.class,
                ()-> employeePayrollService.deleteEmployee(employeeEntity.getId()));
    }
}
