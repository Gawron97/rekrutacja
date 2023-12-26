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
public class ApplicationDetailsDTO {

    private Long id;
//    private String firstname;
//    private String lastname;
//    private String fieldOfStudyName;
    private Integer preferencesNumber;
    private String applicationStatus;
    private Double recruitmentIndicator;

    public static ApplicationDetailsDTO of(Application application) {
        return ApplicationDetailsDTO.builder()
                .id(application.getId())
                .preferencesNumber(application.getPreferencesNumber())
                .applicationStatus(application.getApplicationStatus().name())
                .recruitmentIndicator(application.getRecruitmentIndicator())
                .build();
    }

}
