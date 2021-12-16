package com.bridgelabz.exception;

/**
 * Purpose: To define message in custom exception
 *
 * @author SREELIPTA
 * @since : 04-12-2021
 */

public class EmployeePayrollCustomException extends RuntimeException{

    public EmployeePayrollCustomException(String message) {
        super(message);
    }
}
