package com.example.rekrutacja.repository;

import com.example.rekrutacja.entity.faculty.Specialisation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecializationRepository extends JpaRepository<Specialisation, Long> {
}
