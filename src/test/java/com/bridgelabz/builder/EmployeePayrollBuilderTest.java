package com.bridgelabz.builder;

import com.bridgelabz.dto.EmployeeDto;
import com.bridgelabz.entity.EmployeeEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class EmployeePayrollBuilderTest {

    @InjectMocks
    private EmployeePayrollBuilder employeePayrollBuilder;
    @Mock
    private ModelMapper modelMapper;

    @Test
    void givenEmployeeDto_whenNeedToCovertEmployeeDtoToEmployeeEntity_shouldReturnEmployeeEntity() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Sreelipta");
        employeeDto.setGender("Female");
        employeeDto.setSalary(20000);
        employeeDto.setDepartments((List.of("Cse")));
        employeeDto.setNotes("Regular");
        employeeDto.setJoiningDate("15/01/2021");
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setName("Sreelipta");
        employeeEntity.setGender("Female");
        employeeEntity.setSalary(20000);
        employeeEntity.setDepartments((List.of("Cse")));
        employeeEntity.setNotes("Regular");
        employeeEntity.setJoiningDate("15/01/2021");
        employeeEntity = employeePayrollBuilder.buildEmployeeEntity(employeeDto, employeeEntity);
        assertEquals(employeeDto.getName(), employeeEntity.getName());
        assertEquals(employeeDto.getGender(), employeeEntity.getGender());
        assertEquals(employeeDto.getDepartments(), employeeEntity.getDepartments());
        assertEquals(employeeDto.getImagePath(), employeeEntity.getImagePath());
        assertEquals(employeeDto.getSalary(), employeeEntity.getSalary());
        assertEquals(employeeDto.getNotes(), employeeEntity.getNotes());
    }
}
