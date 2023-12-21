package com.example.rekrutacja.entity.documents;

import com.example.rekrutacja.entity.Recruitment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Application {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double recruitmentIndicator;
    private Integer preferencesNumber;
    @Enumerated(value = EnumType.STRING)
    private ApplicationStatus applicationStatus;
    @ManyToOne
    @JoinColumn(name = "id_recruitment")
    private Recruitment recruitment;
    @OneToOne
    @JoinColumn(name = "id_document")
    private Document document;


}
