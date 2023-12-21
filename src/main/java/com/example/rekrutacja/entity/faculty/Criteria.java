package com.example.rekrutacja.entity.faculty;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.print.attribute.standard.MediaSize;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Criteria {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "field_of_studies_criterias",
    joinColumns = @JoinColumn(name = "id_criteria"),
    inverseJoinColumns = @JoinColumn(name = "id_field_of_study"))
    private List<FieldOfStudy> fieldOfStudies;
    @ManyToOne
    @JoinColumn(name = "id_specialisation")
    private Specialisation specialisation;


}
