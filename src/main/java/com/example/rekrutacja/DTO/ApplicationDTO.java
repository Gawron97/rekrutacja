package com.example.rekrutacja.DTO;

import com.example.rekrutacja.entity.documents.Application;
import com.example.rekrutacja.entity.documents.ApplicationStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDTO {

    private Long id;
    private Double recruitmentIndicator;
    @NotNull
    private Integer preferencesNumber;
    private ApplicationStatus applicationStatus;
    @NotNull
    private Long recruitmentId;
    private Long documentId;

    public static ApplicationDTO of(Application savedApplication) {
        return ApplicationDTO.builder()
                .id(savedApplication.getId())
                .recruitmentIndicator(savedApplication.getRecruitmentIndicator())
                .preferencesNumber(savedApplication.getPreferencesNumber())
                .applicationStatus(savedApplication.getApplicationStatus())
                .recruitmentId(savedApplication.getRecruitment().getId())
                .build();
    }
}
