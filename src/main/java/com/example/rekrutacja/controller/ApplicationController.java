package com.example.rekrutacja.controller;

import com.example.rekrutacja.DTO.ApplicationDTO;
import com.example.rekrutacja.DTO.ApplicationDetailsDTO;
import com.example.rekrutacja.DTO.ApplicationInfoDTO;
import com.example.rekrutacja.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/application")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping("/add")
    public ResponseEntity<ApplicationDTO> addApplication(@RequestBody ApplicationDTO application, Principal principal) {
        return ResponseEntity.ok(applicationService.addApplication(application, principal.getName()));
    }

    @GetMapping
    public ResponseEntity<List<ApplicationInfoDTO>> getApplications() {
        return ResponseEntity.ok(applicationService.getApplications());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationDetailsDTO> getApplicationDetails(@PathVariable Long id) {
        return ResponseEntity.ok(applicationService.getApplicationDetails(id));
    }

}
