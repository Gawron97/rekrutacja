package com.example.rekrutacja.repository;

import com.example.rekrutacja.entity.users.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
}
