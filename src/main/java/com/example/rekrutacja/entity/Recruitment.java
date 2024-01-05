package com.example.rekrutacja.entity;

import com.example.rekrutacja.entity.documents.Application;
import com.example.rekrutacja.entity.faculty.FieldOfStudy;
import com.example.rekrutacja.entity.faculty.Specialization;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Recruitment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 64)
    private String cycle;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @Column(nullable = false)
    private Integer capacity;

    private Double thresholdPoints;

    @ManyToOne
    @JoinColumn(name = "id_field_of_study", nullable = false)
    private FieldOfStudy fieldOfStudy;

    @OneToOne
    @JoinColumn(name = "id_specialisation")
    private Specialization specialization;

    @OneToMany(mappedBy = "recruitment", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Application> applications;
}
