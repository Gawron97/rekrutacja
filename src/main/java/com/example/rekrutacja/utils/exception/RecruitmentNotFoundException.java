package com.example.rekrutacja.utils.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RecruitmentNotFoundException extends RuntimeException {

    private final HttpStatus status;

    public RecruitmentNotFoundException() {
        super("Recruitment not found");
        status = HttpStatus.NOT_FOUND;
    }

    public RecruitmentNotFoundException(String message) {
        super(message);
        status = HttpStatus.NOT_FOUND;
    }

}
