package com.example.rekrutacja.repository;

import com.example.rekrutacja.entity.users.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findAppUserByLogin(String login);

    @Query("SELECT u.id FROM AppUser u WHERE u.login = ?1")
    Optional<Long> findAppUserIdByLogin(String login);

    List<AppUser> findAppUsersByNameAndSurname(String name, String surname);

    Optional<AppUser> findAppUserById(Long id);
}
