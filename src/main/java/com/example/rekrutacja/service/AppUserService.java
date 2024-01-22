package com.example.rekrutacja.service;

import com.example.rekrutacja.entity.users.AppUser;
import com.example.rekrutacja.repository.AppUserRepository;
import com.example.rekrutacja.utils.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserByUsername(username);
    }

    public AppUser getUserByUsername(String username) {
        return appUserRepository.findAppUserByLogin(username).orElseThrow(
                () -> new UsernameNotFoundException("User with username " + username + " not found")
        );
    }

    public AppUser getUserById(Long id) {
        return appUserRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User with id " + id + " not found")
        );
    }

    public Long getIdOfUserByUsername(String username) {
        return appUserRepository.findAppUserIdByLogin(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
    }

    public boolean existsById(Long id) {
        return appUserRepository.existsById(id);
    }

    public List<AppUser> getAllUsersByNameAndSurname(String name, String surname) {
        return appUserRepository.findAppUsersByNameAndSurname(name, surname);
    }
}
