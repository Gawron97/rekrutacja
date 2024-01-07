package com.example.rekrutacja.repository;

import com.example.rekrutacja.entity.faculty.Specialization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecializationRepository extends JpaRepository<Specialization, Long> {

    Page<Specialization> findAllByFieldOfStudyId(Pageable pageable, Long fieldOfStudyId);
}
