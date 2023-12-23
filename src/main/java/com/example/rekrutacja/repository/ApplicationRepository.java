package com.example.rekrutacja.repository;

import com.example.rekrutacja.entity.Recruitment;
import com.example.rekrutacja.entity.documents.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Boolean existsByRecruitment(Recruitment recruitment);

}
