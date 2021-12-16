package com.bridgelabz.exception;

/**
 * Purpose: To define message in custom exception
 *
 * @author SREELIPTA
 * @since : 04-12-2021
 */

public class EmployeeCustomException extends RuntimeException{

    public EmployeeCustomException(String message) {
        super(message);
    }
}
