package com.example.pro.make.exception;

import lombok.Getter;

@Getter
public class DevException extends RuntimeException{

    private DevErrorCode devErrorCode;
    private String detailMessage;

    public DevException(DevErrorCode errorCode){
        super(errorCode.getMessage());
        this.devErrorCode = errorCode;
        this.detailMessage = errorCode.getMessage();
    }
    public DevException(DevErrorCode errorCode, String detailMessage){
        super(errorCode.getMessage());
        this.devErrorCode = errorCode;
        this.detailMessage = detailMessage;
    }
}
