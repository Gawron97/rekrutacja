package com.example.rekrutacja.controller;

import com.example.rekrutacja.DTO.AppUserDTO;
import com.example.rekrutacja.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class AppUserController {

    private final AppUserService appUserService;

    @GetMapping
    public AppUserDTO getLoggedInUserData(
            Principal principal
    ) {
        return appUserService.getUserDetailsByUsername(principal.getName());
    }
}
