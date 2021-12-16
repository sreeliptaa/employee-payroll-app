package com.bridgelabz.dto;

import lombok.Data;

import java.util.Date;

/**
 * Purpose : To define the error details
 *
 * @author SREELIPTA
 * @since 10-12-2021
 */
@Data
public class ResponseDto {
    private Date timeStamp;
    private String message;
    private String details;

    public ResponseDto(Date timeStamp, String message, String details) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.details = details;
    }

}
