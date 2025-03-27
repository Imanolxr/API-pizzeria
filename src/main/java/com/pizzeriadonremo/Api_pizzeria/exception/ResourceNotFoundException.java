package com.pizzeriadonremo.Api_pizzeria.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class ResourceNotFoundException extends RuntimeException {
    private String message;
    private String errorCode;
}
