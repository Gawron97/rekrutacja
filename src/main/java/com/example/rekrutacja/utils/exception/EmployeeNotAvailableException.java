package com.example.rekrutacja.utils.exception;

import org.springframework.http.HttpStatus;

public class EmployeeNotAvailableException extends AppException {

    public EmployeeNotAvailableException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
