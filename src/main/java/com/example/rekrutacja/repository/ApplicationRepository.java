package com.example.rekrutacja.repository;

import com.example.rekrutacja.DTO.ApplicationInfoDTO;
import com.example.rekrutacja.entity.Recruitment;
import com.example.rekrutacja.entity.documents.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Boolean existsByRecruitment(Recruitment recruitment);

    @Query("SELECT a.id, a.candidate.name, a.candidate.surname, a.recruitment.fieldOfStudy.name " +
            "FROM Application a")
    List<ApplicationInfoDTO> getApplicationsInfo();

}
