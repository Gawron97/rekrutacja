package com.example.rekrutacja.controller;

import com.example.rekrutacja.entity.users.ActivityStatus;
import com.example.rekrutacja.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PatchMapping("/active-status")
    public void changeActiveStatus(
            @RequestParam ActivityStatus status,
            Principal principal
    ) {
        employeeService.changeActiveStatus(status, principal.getName());
    }
}
