package com.example.rekrutacja.entity.documents;

import com.example.rekrutacja.entity.Recruitment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Application extends Document {

    private Double recruitmentIndicator;

    private Integer preferencesNumber;

    @Enumerated(value = EnumType.STRING)
    private ApplicationStatus applicationStatus;

    @ManyToOne
    @JoinColumn(name = "id_recruitment")
    private Recruitment recruitment;

}
