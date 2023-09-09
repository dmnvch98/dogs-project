package com.example.dogsproject.exceptions.hanlder;

import com.example.dogsproject.exceptions.AppException;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<Error> handleException(AppException e) {
        Error error = Error.builder()
            .message(e.getMessage())
            .responseCode(e.getResponseCode().value())
            .localDateTime(LocalDateTime.now())
            .build();
        return ResponseEntity.status(e.getResponseCode()).body(error);
    }

    @Builder
    @Data
    private static class Error {
        private String message;
        private int responseCode;
        private LocalDateTime localDateTime;
    }
}
