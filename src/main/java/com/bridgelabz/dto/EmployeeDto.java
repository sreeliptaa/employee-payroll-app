package com.bridgelabz.dto;

import lombok.Data;

import javax.validation.constraints.*;

/**
 * Purpose : To invoke data from client
 *
 * @author SREELIPTA
 * @since 06-12-2021
 */
@Data
public class EmployeeDto {
    @NotNull
    @Pattern(regexp = "^[A-Z][a-z]{2,}$", message = "Name should be start with capital latter " +
            "& should contain more then 3 character ")
    private String name;

    @NotNull
    @Min(value = 10000, message = "Minimum wage should be more than 10000")
    private double salary;

    @NotNull
    @Pattern(regexp = "^(?:m|M|male|Male|f|F|female|Female|o|O|Other|other)$", message = "Please type gender F - female " +
            "M - male , O - others/Transgender")
    private String gender;

    @NotNull
    private String joiningDate;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z]{2,}$", message = "Department should be start with capital latter " +
            "& should contain more then 2 character ")
    private String department;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Notes should be start with capital latter " +
            "& should contain more then 3 character ")
    private String notes;

    @NotNull
    private String imagePath;
}
