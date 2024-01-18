package com.example.rekrutacja.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Deprecated(forRemoval = true)
@RequestMapping("/test")
public class TestController {

    @GetMapping
    @SecurityRequirements
    public String test() {
        return "test";
    }

    @GetMapping("/secured")
    public String testSecured() {
        return "test secured";
    }
}
