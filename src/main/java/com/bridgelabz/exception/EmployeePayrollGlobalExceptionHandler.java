package com.bridgelabz.exception;

import com.bridgelabz.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ValidationException;

/**
 * Purpose: To handle all the exceptions in EmployeePayroll
 *
 * @author SREELIPTA
 * @since : 04-12-2021
 */
@ControllerAdvice
public class 