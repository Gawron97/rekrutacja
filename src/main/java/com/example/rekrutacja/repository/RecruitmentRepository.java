package com.example.rekrutacja.repository;

import com.example.rekrutacja.entity.Recruitment;
import com.example.rekrutacja.entity.faculty.FieldOfStudy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {

    @Query("SELECT r " +
            "FROM Recruitment r " +
            "WHERE r.fieldOfStudy.name = :name AND r.endDate > CURRENT_DATE")
    Optional<Recruitment> findRecruitmentByFieldOfStudy_Name(String name);

    @Query("""
            SELECT r
            FROM Recruitment r
            WHERE
                LOWER(r.fieldOfStudy.name) LIKE LOWER(CONCAT('%', :search, '%')) OR
                LOWER(r.cycle) LIKE LOWER(CONCAT('%', :search, '%'))
            """)
    Page<Recruitment> findRecruitmentsByFieldOfStudyOrCycleOrSpecializationName(String search, Pageable pageable);

    @Query("SELECT r.fieldOfStudy " +
            "FROM Recruitment r " +
            "WHERE r.endDate > CURRENT_DATE ")
    List<FieldOfStudy> findFieldOfStudiesForActiveRecruitments();
}
