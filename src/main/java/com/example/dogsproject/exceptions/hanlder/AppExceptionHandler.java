package com.example.dogsproject.exceptions.hanlder;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@ControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Error> handleException(ResponseStatusException e) {
        Error error = Error.builder()
            .message(e.getReason())
            .httpStatusCode(e.getStatusCode())
            .localDateTime(LocalDateTime.now())
            .build();
        return ResponseEntity.status(e.getStatusCode()).body(error);
    }

    @Builder
    @Data
    private static class Error {
        private String message;
        private HttpStatusCode httpStatusCode;
        private LocalDateTime localDateTime;
    }
}
