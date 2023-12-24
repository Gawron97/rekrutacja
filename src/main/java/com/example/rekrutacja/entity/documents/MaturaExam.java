package com.example.rekrutacja.entity.documents;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Data
@SuperBuilder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MaturaExam extends Document {

    @OneToMany(mappedBy = "maturaExam")
    private Set<PassingSubject> passingSubjects;
    @Enumerated(value = EnumType.STRING)
    private DocumentStatus documentStatus;

}
