package com.example.rekrutacja.repository;

import com.example.rekrutacja.entity.documents.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
