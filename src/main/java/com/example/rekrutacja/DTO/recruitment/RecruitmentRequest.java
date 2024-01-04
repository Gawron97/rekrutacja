package com.example.rekrutacja.DTO.recruitment;

import com.example.rekrutacja.utils.TimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record RecruitmentRequest(
        String cycle,
        @JsonFormat(pattern = TimeFormat.DATE_TIME_FORMAT)

        LocalDateTime startDate,
        @JsonFormat(pattern = TimeFormat.DATE_TIME_FORMAT)

        LocalDateTime endDate,
        Integer capacity,
        Long fieldOfStudyId,
        Long specializationId
) {
}
