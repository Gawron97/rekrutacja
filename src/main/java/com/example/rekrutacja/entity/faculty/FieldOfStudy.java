package com.example.rekrutacja.entity.faculty;

import com.example.rekrutacja.entity.PreferencesTest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
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

    @OneToMany(mappedBy = "fieldOfStudy", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Specialisation> specialisations;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "field_of_studies_criterias",
            joinColumns = @JoinColumn(name = "id_field_of_study"),
            inverseJoinColumns = @JoinColumn(name = "id_criteria"))
    private Set<Criteria> criterias;

    public FieldOfStudy() {
        specialisations = new HashSet<>();
        criterias = new HashSet<>();
    }

    public void addCriteria(Criteria criteria) {
        criterias.add(criteria);
    }

}
