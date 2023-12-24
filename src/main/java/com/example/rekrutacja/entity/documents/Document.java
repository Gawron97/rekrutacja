package com.example.rekrutacja.entity.documents;

import com.example.rekrutacja.entity.users.AppUser;
import com.example.rekrutacja.entity.users.Candidate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Document {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime addDate;
    @ManyToOne
    @JoinColumn(name = "id_candidate")
    private Candidate candidate;

}
