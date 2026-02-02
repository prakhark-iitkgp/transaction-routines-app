package com.example.demo.controller;

import com.example.demo.dto.ServiceResponse;
import com.example.demo.exception.AccountNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TransactionControllerAdvice {

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ServiceResponse> handleAccountNotFound(AccountNotFoundException ex) {
        ServiceResponse response = new ServiceResponse(
                ex.getMessage(),
                false,
                null
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
