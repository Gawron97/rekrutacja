package com.example.rekrutacja.controller;

import com.example.rekrutacja.service.FieldOfStudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/field-of-study")
@RequiredArgsConstructor
public class FieldOfStudyController {

    private final FieldOfStudyService fieldOfStudyService;

    @GetMapping("/names")
    public ResponseEntity<List<String>> getFieldOfStudiesNames() {
        return ResponseEntity.ok(fieldOfStudyService.getFieldOfStudiesNames());
    }

}
