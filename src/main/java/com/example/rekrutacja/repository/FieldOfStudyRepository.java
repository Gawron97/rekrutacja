package com.example.rekrutacja.repository;

import com.example.rekrutacja.entity.faculty.Criteria;
import com.example.rekrutacja.entity.faculty.FieldOfStudy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FieldOfStudyRepository extends JpaRepository<FieldOfStudy, Long> {

    @Query("SELECT c " +
            "FROM FieldOfStudy fos " +
            "JOIN fos.criterias c " +
            "WHERE fos.id = :id")
    List<Criteria> findAllCriteriaForFieldOfStudy(Long id);

}
