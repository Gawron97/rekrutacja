package com.example.rekrutacja.entity.faculty;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@Entity
@AllArgsConstructor
public class FieldOfStudy {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(value = EnumType.STRING)
    private StudyMode studyMode;

    @Enumerated(value = EnumType.STRING)
    private DegreeOfStudy degreeOfStudy;

    private String description;

    private String recruitmentRateTemplate;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "id")
    private Set<Specialization> specializations;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "field_of_studies_criterias",
            joinColumns = @JoinColumn(name = "id_field_of_study"),
            inverseJoinColumns = @JoinColumn(name = "id_criteria"))
    private Set<Criteria> criterias;

    public FieldOfStudy() {
        specializations = new HashSet<>();
        criterias = new HashSet<>();
    }

    public void addCriteria(Criteria criteria) {
        criterias.add(criteria);
    }

}
