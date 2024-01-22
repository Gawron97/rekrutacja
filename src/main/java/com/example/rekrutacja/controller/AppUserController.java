package com.example.rekrutacja.controller;

import com.example.rekrutacja.entity.users.AppUser;
import com.example.rekrutacja.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

}
