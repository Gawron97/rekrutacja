package com.example.rekrutacja.entity;


import com.example.rekrutacja.entity.faculty.FieldOfStudy;
import com.example.rekrutacja.entity.users.Candidate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PreferencesTest {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_candidate")
    private Candidate candidate;

    @ManyToMany
    @JoinTable(name = "test_results",
    joinColumns = @JoinColumn(name = "id_test"),
    inverseJoinColumns = @JoinColumn(name = "id_field_of_study"))
    private Set<FieldOfStudy> fieldOfStudies;

}
