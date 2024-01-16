package com.example.rekrutacja.service;

import com.example.rekrutacja.DTO.AppUserDTO;
import com.example.rekrutacja.entity.users.AppUser;
import com.example.rekrutacja.repository.AppUserRepository;
import com.example.rekrutacja.service.mapper.AppUserMapper;
import com.example.rekrutacja.utils.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final AppUserMapper appUserMapper = AppUserMapper.INSTANCE;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserByUsername(username);
    }

    public AppUser getUserByUsername(String username) {
        return appUserRepository.findAppUserByLogin(username).orElseThrow(
                () -> new UsernameNotFoundException("User with username " + username + " not found")
        );
    }

    public AppUserDTO getUserDetailsByUsername(String username) {
        return appUserMapper.appUserToAppUserDTO(getUserByUsername(username));
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
}
