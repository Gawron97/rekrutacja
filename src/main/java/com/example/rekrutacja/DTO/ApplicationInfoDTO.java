package com.example.rekrutacja.DTO;

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

}
