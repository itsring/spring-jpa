package com.example.pro.make.dto;


import com.example.pro.make.exception.DevErrorCode;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DevResponse {
    private DevErrorCode errorCode;
    private String errorMessage;
}
