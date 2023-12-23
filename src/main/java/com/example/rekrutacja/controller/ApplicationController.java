package com.example.rekrutacja.controller;

import com.example.rekrutacja.DTO.ApplicationDTO;
import com.example.rekrutacja.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/application")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping("/add")
    public ResponseEntity<ApplicationDTO> addApplication(@RequestBody ApplicationDTO application, Principal principal) {
        return ResponseEntity.ok(applicationService.addApplication(application, principal.getName()));
    }

}
