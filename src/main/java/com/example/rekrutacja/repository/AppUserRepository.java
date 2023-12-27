package com.example.rekrutacja.repository;

import com.example.rekrutacja.entity.users.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findAppUserByLogin(String login);
}
