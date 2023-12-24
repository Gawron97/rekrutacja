package com.example.rekrutacja.entity.documents;

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
public class Certificate extends Document {

    @Enumerated(value = EnumType.STRING)
    private CertificateType certificateType;
    private Double result;
    private LocalDate validityDate;
    @Enumerated(value = EnumType.STRING)
    private DocumentStatus documentStatus;

}
