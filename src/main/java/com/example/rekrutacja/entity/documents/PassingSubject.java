package com.example.rekrutacja.entity.documents;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "name")
public class PassingSubject {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double result;
    @ManyToOne
    @JoinColumn(name = "id_matura_exam")
    private MaturaExam maturaExam;

}
