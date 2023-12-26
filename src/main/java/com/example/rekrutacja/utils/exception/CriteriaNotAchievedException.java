package com.example.rekrutacja.utils.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CriteriaNotAchievedException extends RuntimeException {

    private final HttpStatus status;

    public CriteriaNotAchievedException() {
        super("Required Criteria not achieved");
        status = HttpStatus.BAD_REQUEST;
    }

    public CriteriaNotAchievedException(String message) {
        super(message);
        status = HttpStatus.BAD_REQUEST;
    }
}
