package com.example.rekrutacja.entity.documents;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Entity
@AllArgsConstructor
public class MaturaExam extends Document {

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "maturaExam", cascade = CascadeType.ALL)
    private Set<PassingSubject> passingSubjects;
    @Enumerated(value = EnumType.STRING)
    private DocumentStatus documentStatus;

    public MaturaExam() {
        passingSubjects = new HashSet<>();
    }

    public void addPassingSubject(PassingSubject passingSubject) {
        passingSubjects.add(passingSubject);
        passingSubject.setMaturaExam(this);
    }

}
