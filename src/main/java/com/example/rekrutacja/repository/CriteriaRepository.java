package com.example.rekrutacja.repository;

import com.example.rekrutacja.entity.faculty.Criteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CriteriaRepository extends JpaRepository<Criteria, Long> {

    @Query("SELECT c " +
            "FROM Criteria c " +
            "JOIN c.fieldOfStudies fos " +
            "WHERE fos.id = :id")
    List<Criteria> findAllByFieldOfStudiesName(Long id);

}
