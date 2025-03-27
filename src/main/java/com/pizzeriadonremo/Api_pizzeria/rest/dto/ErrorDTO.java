package com.pizzeriadonremo.Api_pizzeria.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDTO {
    private String message;
    private String errorCode;
    private LocalDateTime timeStamp;
    private String detail;
    private String path;
}
