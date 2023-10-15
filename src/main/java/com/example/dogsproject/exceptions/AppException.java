package com.example.dogsproject.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class AppException extends RuntimeException {
    private HttpStatus responseCode;

    public AppException(String message, HttpStatus responseCode) {
        super(message);
        this.responseCode = responseCode;
    }
}
