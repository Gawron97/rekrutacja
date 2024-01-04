package com.example.rekrutacja.repository;

import com.example.rekrutacja.entity.Recruitment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {

    Optional<Recruitment> findRecruitmentByFieldOfStudy_Name(String name);

    @Query("SELECT r FROM Recruitment r WHERE r.fieldOfStudy.name LIKE %?1% OR r.cycle LIKE %?1%")
    Page<Recruitment> findRecruitmentByFieldOfStudyOrCycleName(String search, Pageable pageable);
}
