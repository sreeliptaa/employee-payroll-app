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
public class EmployeePayrollGlobalException {

    /**
     * Purpose : To provide the exception message in the method level
     *
     * @param customException : takes the exception class to provide the exception message
     * @return a new response entity instance
     */
    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<ResponseDto> handleCustomException(CustomException customException) {
        return new ResponseEntity<>(new ResponseDto(customException.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

    /**
     * Purpose : To provide the constraint exception message in the controller level
     *
     * @param validationException : takes the Web request to access the metadata of general request
     * @return a response entity instance
     */
    @ExceptionHandler(value = ValidationException.class)
    public ResponseEntity<ResponseDto> handleValidationException(ValidationException validationException){
        return new ResponseEntity<>(new ResponseDto(validationException.getMessage(), null), HttpStatus.NOT_FOUND);
    }
}
