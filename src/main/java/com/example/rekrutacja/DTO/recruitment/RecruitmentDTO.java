package com.example.rekrutacja.DTO.recruitment;

import com.example.rekrutacja.DTO.FieldOfStudyDTO;
import com.example.rekrutacja.DTO.SpecializationDTO;
import com.example.rekrutacja.utils.TimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record RecruitmentDTO(
        Long id,
        String cycle,
        @JsonFormat(pattern = TimeFormat.DATE_FORMAT)
        LocalDate startDate,
        @JsonFormat(pattern = TimeFormat.DATE_FORMAT)
        LocalDate endDate,
        Integer capacity,
        FieldOfStudyDTO fieldOfStudy,
        SpecializationDTO specialization,
        Double thresholdPoints
) {
}
