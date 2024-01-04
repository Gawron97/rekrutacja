package com.example.rekrutacja.repository;

import com.example.rekrutacja.entity.faculty.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecializationRepository extends JpaRepository<Specialization, Long> {
}
