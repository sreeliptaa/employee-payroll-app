package com.bridgelabz.exception;

import com.bridgelabz.dto.ResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.Valid;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Purpose: To handle all the exceptions in EmployeePayroll
 *
 * @author SREELIPTA
 * @since : 04-12-2021
 */
@ControllerAdvice
public class EmployeePayrollGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * Purpose : Returns a response for MethodArgumentNotValidException.
     *
     * @param ex      the exception
     * @param headers the headers to be written to the response
     * @param status  the selected response status
     * @param request the current request
     * @return the ResponseEntity instance
     */
    @Valid
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());
        body.put("errors", errors);
        return new ResponseEntity<>(body, headers, status);
    }

    /**
     * Purpose : To provide the exception message in the method level
     *
     * @param : takes the exception class to provide the exception message
     * @return a new response entity instance
     */
    @ExceptionHandler(EmployeePayrollCustomException.class)
    public ResponseEntity handleEmployeeCustomException(EmployeePayrollCustomException exception, WebRequest request) {
        ResponseDto responseDto = new ResponseDto(new Date(), exception.getMessage(),
                request.getDescription(false));
        return new ResponseEntity(responseDto, HttpStatus.NOT_FOUND);
    }

    /**
     * Purpose : To handle Http Request Method Not Supported Exception.
     *
     * @param ex      : ex used for Http Request Method Not Supported Exception.
     * @param headers : headers is used for HTTP headers of the HTTP message.
     * @param status  : status used to describe HTTP status code is a server response to a browser's request.
     * @param request : request is used to describe web request that is a communicative message,
     *                that is transmitted between the client or web browsers, to the servers.
     * @return : Please Check http Method Type for the bad request http status.
     */
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        return new ResponseEntity<Object>("Please Check Method Type", HttpStatus.BAD_REQUEST);
    }

}