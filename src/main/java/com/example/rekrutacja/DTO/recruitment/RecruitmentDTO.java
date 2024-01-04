package com.example.rekrutacja.DTO.recruitment;

import com.example.rekrutacja.DTO.FieldOfStudyDTO;
import com.example.rekrutacja.DTO.SpecializationDTO;

import java.time.LocalDateTime;

public record RecruitmentDTO(
        Long id,
        String cycle,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Integer capacity,
        FieldOfStudyDTO fieldOfStudy,
        SpecializationDTO specialization
) {
}
