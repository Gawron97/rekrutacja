package com.example.rekrutacja.utils;

import com.example.rekrutacja.entity.AppUserRole;
import com.example.rekrutacja.entity.users.AppUser;
import com.example.rekrutacja.repository.AppUserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Profile("dev")
@RequiredArgsConstructor
public class PostConstructMockDataCreator {

    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        if(userRepository.count() > 0)
            return;

        log.info("Creating mock data in database...");
        createOneUserForEveryRole();
        log.info("Mock data successfully created!");
    }

    private void createOneUserForEveryRole() {
        var admin = createUserWithProperties(
                "admin",
                "pass",
                AppUserRole.ADMIN
        );
        var user = createUserWithProperties(
                "candidate",
                "pass",
                AppUserRole.CANDIDATE
        );
        var administration = createUserWithProperties(
                "administration",
                "pass",
                AppUserRole.ADMINISTRATION_EMPLOYEE
        );

        userRepository.save(admin);
        userRepository.save(user);
        userRepository.save(administration);
    }

    private AppUser createUserWithProperties(String username, String password, AppUserRole role) {
        return AppUser.builder()
                .name("name")
                .surname("surname")
                .login(username)
                .password(passwordEncoder.encode(password))
                .role(role)
                .pesel("12345678901")
                .email(username + "@example.com")
                .build();
    }
}
