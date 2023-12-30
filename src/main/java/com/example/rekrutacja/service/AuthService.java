package com.example.rekrutacja.service;

import com.example.rekrutacja.DTO.AuthResponse;
import com.example.rekrutacja.DTO.LoginRequest;
import com.example.rekrutacja.repository.AppUserRepository;
import com.example.rekrutacja.utils.exception.LoginException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse login(LoginRequest request) {
        var user =  appUserRepository.findAppUserByLogin(request.login()).orElseThrow(
                () -> new LoginException("Bad username or password")
        );

        if (!passwordEncoder.matches(request.password(), user.getPassword()))
            throw new LoginException("Bad username or password");

        return new AuthResponse(jwtService.generateToken(user), user.getRole());
    }
}
