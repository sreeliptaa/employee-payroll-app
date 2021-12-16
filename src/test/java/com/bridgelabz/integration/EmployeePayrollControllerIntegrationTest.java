package com.bridgelabz.integration;

import com.bridgelabz.controller.EmployeePayrollController;
import com.bridgelabz.dto.EmployeeDto;
import com.bridgelabz.service.EmployeePayrollService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeePayrollController.class)
@ActiveProfiles("test")
public class EmployeePayrollControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeePayrollService employeePayrollService;

    @Test
    void getListOfEmployeeTest() throws Exception {
        when(employeePayrollService.getListOfAllEmployee()).thenReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders.get("/employee/detail"))
                .andExpect(status().isOk());
    }

    @Test
    void addEmployeeTest() throws Exception {
        when(employeePayrollService.addEmployee(any())).thenReturn("success");
        mockMvc.perform(MockMvcRequestBuilders
                .post("/employee/detail")
                .content("{\"name\":\"Sreelipta\",\"salary\":50000,\"gender\":\"Female\"," +
                        "\"joiningDate\":\"09/21/2021\",\"department\":[\"Sales,Development,Testing\"]," +
                        "\"notes\":\"regular\",\"imagePath\":\"s.jpg\"}")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void updateEmployeeTest() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Sreelipta");
        employeeDto.setSalary(20000);
        employeeDto.setGender("Female");
        employeeDto.setDepartment((List.of("Cse")));
        employeeDto.setNotes("Regular");
        employeeDto.setJoiningDate("15-01-2021");
        employeeDto.setImagePath("image.jpg");
        int id = 1;
        when(employeePayrollService.updateEmployee(id, employeeDto)).thenReturn("success");
        mockMvc.perform(MockMvcRequestBuilders
                .put("/employee/detail/1")
                .content("{\"name\":\"Sreelipta\",\"salary\":50000,\"gender\":\"Female\"," +
                        "\"joiningDate\":\"09/21/2021\",\"department\":[\"Sales,Development,Testing\"]," +
                        "\"notes\":\"regular\",\"imagePath\":\"s.jpg\"}")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void deleteEmployeeTest() throws Exception {
        when(employeePayrollService.deleteEmployee(1)).thenReturn("success");
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/employee/detail/1")
                .content("{\"name\":\"Sreelipta\",\"salary\":50000,\"gender\":\"Female\"," +
                        "\"joiningDate\":\"09/21/2021\",\"department\":[\"Sales,Development,Testing\"]," +
                        "\"notes\":\"regular\",\"imagePath\":\"s.jpg\"}")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }
}
