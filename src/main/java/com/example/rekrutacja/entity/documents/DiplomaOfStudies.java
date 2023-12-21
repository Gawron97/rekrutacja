package com.example.rekrutacja.entity.documents;

import com.example.rekrutacja.entity.faculty.DegreeOfStudy;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class DiplomaOfStudies {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double grade;
    private String fieldOfStudyName;
    private LocalDate EndDate;
    @Enumerated(value = EnumType.STRING)
    private DegreeOfStudy degreeOfStudy;

}
