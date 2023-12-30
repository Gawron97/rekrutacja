package com.example.rekrutacja.repository;

import com.example.rekrutacja.entity.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {

    Optional<Recruitment> findRecruitmentByFieldOfStudy_Name(String name);

}
