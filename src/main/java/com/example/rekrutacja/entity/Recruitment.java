package com.example.rekrutacja.entity;

import com.example.rekrutacja.entity.faculty.FieldOfStudy;
import com.example.rekrutacja.entity.faculty.Specialisation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Recruitment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer cycle;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer capacity;
    private Double thresholdPoints;

    @ManyToOne
    @JoinColumn(name = "id_field_of_study", nullable = false)
    private FieldOfStudy fieldOfStudy;

    @OneToOne
    @JoinColumn(name = "id_specialisation")
    private Specialisation specialisation;

}
