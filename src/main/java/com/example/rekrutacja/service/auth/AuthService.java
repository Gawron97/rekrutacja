package com.example.rekrutacja.service.auth;

import com.example.rekrutacja.DTO.AuthResponse;
import com.example.rekrutacja.DTO.LoginRequest;
import com.example.rekrutacja.entity.AppUserRole;
import com.example.rekrutacja.repository.AppUserRepository;
import com.example.rekrutacja.utils.exception.LoginException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserDetailsServiceImpl userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse login(LoginRequest request) {

        UserDetails user;

        try {
            user = userDetailsService.loadUserByUsername(request.login());
        } catch (UsernameNotFoundException e) {
            throw new LoginException("Bad username or password");
        }

        if (!passwordEncoder.matches(request.password(), user.getPassword()))
            throw new LoginException("Bad username or password");

        return new AuthResponse(jwtService.generateToken(user), getRole(user.getAuthorities()));
    }

    private AppUserRole getRole(Collection<? extends GrantedAuthority> authorities) {
        System.out.println(authorities.stream().findFirst().get().getAuthority().substring(5));
        return AppUserRole.valueOf(authorities.stream().findFirst().get().getAuthority().substring(5));
    }

}
