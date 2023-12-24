package com.example.rekrutacja.repository;

import com.example.rekrutacja.entity.users.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    Optional<Candidate> findByLogin(String login);

}
