package com.bridgelabz.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Purpose : To invoke data from client
 *
 * @author SREELIPTA
 * @since 06-12-2021
 */
@Data
public class EmployeeDto {
    @Pattern(regexp = "[A-Z][a-z]{2,}$", message = "Not a Valid Name")
    private String name;

    private double salary;

    @Size(message = "This should be within 10 letters", max = 10)
    private String gender;

    @Size(message = "This should be within 15 letters", max = 15)
    private String department;

    @Size(message = "This should be within 50 letters", max = 50)
    private String notes;

}
