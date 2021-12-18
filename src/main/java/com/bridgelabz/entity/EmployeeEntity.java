package com.bridgelabz.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Purpose : To contain the entities in the database
 *
 * @author SREELIPTA
 * @since 06-12-2021
 */
@Entity
@Table(name = "employee_payroll")
@Data
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SALARY")
    private double salary;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "START_DATE")
    private String joiningDate;

    @ElementCollection
    @CollectionTable(name = "employee_department", joinColumns = @JoinColumn(name = "department_id"))
    @Column(name="DEPARTMENT")
    private List<String> departments;

    @Column(name = "NOTES")
    private String notes;

    @Column(name = "IMAGE_PATH")
    private String imagePath;


}
