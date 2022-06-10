package com.example.pro.make.exception;


import com.example.pro.make.dto.DevResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

import static com.example.pro.make.exception.DevErrorCode.INVALID_REQUEST;

@Slf4j
@RestControllerAdvice
public class DevExceptionHandler {

    @ExceptionHandler(DevException.class)
    public DevResponse handleException(
            DevException e,
            HttpServletRequest request){
        log.error("errorCode : {}, url : {}, message:{}", e.getDevErrorCode(),request.getRequestURL(), e.getDetailMessage());
        return DevResponse.builder()
                .errorCode(e.getDevErrorCode())
                .errorMessage(e.getDetailMessage())
                .build();
    }
    @ExceptionHandler(value = {
            HttpRequestMethodNotSupportedException.class,
            MethodArgumentNotValidException.class
    })
    public DevResponse handleBadRequest(
            Exception e, HttpServletRequest request
    ){
        log.error("url : {}, message:{}", request.getRequestURL(), e.getMessage());
        return DevResponse.builder()
                .errorCode(INVALID_REQUEST)
                .errorMessage(INVALID_REQUEST.getMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    public DevResponse handleException(
            Exception e, HttpServletRequest request
    ){
        log.error("url : {}, message:{}", request.getRequestURL(), e.getMessage());
        return DevResponse.builder()
                .errorCode(INVALID_REQUEST)
                .errorMessage(INVALID_REQUEST.getMessage())
                .build();
    }

}
