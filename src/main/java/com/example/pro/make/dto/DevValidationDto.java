package com.example.pro.make.dto;


import com.example.pro.make.exception.DevErrorCode;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DevValidationDto {
    private DevErrorCode devErrorCode;
    private String errorMessage;

}
