package com.example.rekrutacja.entity.documents;

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
public class Certificate {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private CertificateType certificateType;
    private Double result;
    private LocalDate validityDate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_document")
    private Document document;

}
