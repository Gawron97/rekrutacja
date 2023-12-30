package com.example.rekrutacja.entity.documents;

import com.example.rekrutacja.entity.faculty.DegreeOfStudy;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@SuperBuilder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class DiplomaOfStudies extends Document {

    private Double grade;
    private String fieldOfStudyName;
    private LocalDate endDate;
    @Enumerated(value = EnumType.STRING)
    private DegreeOfStudy degreeOfStudy;
    @Enumerated(value = EnumType.STRING)
    private DocumentStatus documentStatus;

}
