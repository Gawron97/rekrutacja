package com.example.rekrutacja.controller;

import com.example.rekrutacja.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.rekrutacja.entity.users.AppUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.security.Principal;

@RestController
@RequestMapping("/userdata")
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;


    @GetMapping("/users")
    public ResponseEntity<List<AppUser>> getAllUsersByNameAndSurname(
            @RequestParam String name,
            @RequestParam String surname) {
        return ResponseEntity.ok(appUserService.getAllUsersByNameAndSurname(name, surname));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<AppUser> getUserById(
            @PathVariable Long id) {
        return ResponseEntity.ok(appUserService.getUserById(id));
    }

    @GetMapping
    public AppUserDTO getLoggedInUserData(
            Principal principal
    ) {
        return appUserService.getUserDetailsByUsername(principal.getName());
    }
}
