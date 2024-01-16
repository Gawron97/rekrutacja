package com.example.rekrutacja.DTO;

import com.example.rekrutacja.entity.AppUserRole;

public record AppUserDTO(
        Long id,
        String name,
        String surname,
        String email,
        AppUserRole role
) {
}
