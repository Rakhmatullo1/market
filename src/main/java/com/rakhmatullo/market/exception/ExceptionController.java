package com.rakhmatullo.market.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler
    public ResponseEntity<RestError> handler(GoodNotFoundException e) {
        RestError error = RestError.builder()
                .message(e.getMessage())
                .timestamp(System.currentTimeMillis())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<RestError> handler(UserExistedException e) {
        RestError error = RestError.builder()
                .message(e.getMessage())
                .statusCode(HttpStatus.FORBIDDEN.value())
                .timestamp(System.currentTimeMillis())
                .build();
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
    public ResponseEntity<RestError> handler(StatusNotValid e) {
        RestError error = RestError.builder()
                .message(e.getMessage())
                .statusCode(HttpStatus.FORBIDDEN.value())
                .timestamp(System.currentTimeMillis())
                .build();
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }
}
