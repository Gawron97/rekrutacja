package com.example.rekrutacja.repository;

import com.example.rekrutacja.entity.faculty.Criteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CriteriaRepository extends JpaRepository<Criteria, Long> {


}
