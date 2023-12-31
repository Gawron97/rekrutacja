package com.example.rekrutacja.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Deprecated(forRemoval = true)
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public String test() {
        return "test";
    }

    @GetMapping("/secured")
    public String testSecured() {
        return "test secured";
    }
}
