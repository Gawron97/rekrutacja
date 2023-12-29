package com.example.rekrutacja.utils.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(ApplicationNotFoundException.class)
    public ResponseEntity<String> applicationNotFoundException(ApplicationNotFoundException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }

    @ExceptionHandler(ApplicationDuplicationException.class)
    public ResponseEntity<String> applicationNotFoundException(ApplicationDuplicationException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }

    @ExceptionHandler(CandidateNotFoundException.class)
    public ResponseEntity<String> applicationNotFoundException(CandidateNotFoundException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }

    @ExceptionHandler(CriteriaNotAchievedException.class)
    public ResponseEntity<String> applicationNotFoundException(CriteriaNotAchievedException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }

    @ExceptionHandler(MaturaExamNotFound.class)
    public ResponseEntity<String> applicationNotFoundException(MaturaExamNotFound ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }

    @ExceptionHandler(RecruitmentNotFoundException.class)
    public ResponseEntity<String> applicationNotFoundException(RecruitmentNotFoundException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<String> applicationNotFoundException(LoginException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }

}
