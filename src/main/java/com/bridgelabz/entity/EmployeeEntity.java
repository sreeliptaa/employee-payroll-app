package com.bridgelabz.entity;

import lombok.Data;
import javax.persistence.*;

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
    @Column(name = "EMP_ID")
    private int id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "SALARY")
    private double salary;

    @Column(name = "GENDER", nullable = false)
    private String gender;

    @Column(name = "DEPT")
    private String department;

    @Column(name = "NOTES")
    private String notes;
}
