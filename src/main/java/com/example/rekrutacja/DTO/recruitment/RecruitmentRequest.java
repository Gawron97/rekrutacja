package com.example.rekrutacja.DTO.recruitment;

import com.example.rekrutacja.utils.TimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record RecruitmentRequest(
        String cycle,
        @JsonFormat(pattern = TimeFormat.DATE_FORMAT)

        LocalDate startDate,
        @JsonFormat(pattern = TimeFormat.DATE_FORMAT)

        LocalDate endDate,
        Integer capacity,
        Long fieldOfStudyId,
        Long specializationId
) {
}
