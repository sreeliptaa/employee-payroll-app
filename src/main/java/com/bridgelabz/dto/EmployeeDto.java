package com.bridgelabz.dto;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.List;

/**
 * Purpose : To invoke data from client
 *
 * @author SREELIPTA
 * @since 06-12-2021
 */
@Data
public class EmployeeDto {
    @NotNull(message = "Name should not be empty")
    @Pattern(regexp = "^[A-Z][a-z]{2,}$", message = "Not a valid Name")
    private String name;

    @NotNull(message = "Salary should not be empty")
    @Min(value = 10000, message = "Minimum wage should be more than 10000")
    private double salary;

    @NotNull(message = "Gender field should not be empty")
    @Pattern(regexp = "Male|Female", message = "Gender Should be either Male or Female")
    private String gender;

    @NotNull(message = "Joining date should not be empty")
    @Pattern(regexp = "^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$",
            message = "Date should be maintain the format : mm/dd/yyyy")
    private String joiningDate;

    @NotNull(message = "Department name should not be empty")
    @Size(message = "Department name Should be within 50 letters", max = 50)
    private List<String> department;

    @NotNull(message = "Notes should not be empty")
    @Size(message = "Notes should be within 150 letters", max = 150)
    private String notes;

    @NotBlank(message = "Image can not be empty")
    private String imagePath;

}
