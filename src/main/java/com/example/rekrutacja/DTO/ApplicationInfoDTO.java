package com.example.rekrutacja.DTO;

import com.example.rekrutacja.entity.documents.Application;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationInfoDTO {

    private Long id;
    private String firstname;
    private String lastname;
    private String fieldOfStudyName;
    private Integer preferencesNumber;
    private String applicationStatus;
    private Double recruitmentIndicator;

    public static ApplicationInfoDTO of(Application application) {
        return ApplicationInfoDTO.builder()
                .id(application.getId())
                .firstname(application.getCandidate().getName())
                .lastname(application.getCandidate().getSurname())
                .fieldOfStudyName(application.getRecruitment().getFieldOfStudy().getName())
                .preferencesNumber(application.getPreferencesNumber())
                .applicationStatus(application.getApplicationStatus().name())
                .recruitmentIndicator(application.getRecruitmentIndicator())
                .build();
    }

}
