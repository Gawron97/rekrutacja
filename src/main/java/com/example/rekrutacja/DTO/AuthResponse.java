package com.example.rekrutacja.DTO;

import com.example.rekrutacja.entity.AppUserRole;

public record AuthResponse(String token, AppUserRole role) {
}
