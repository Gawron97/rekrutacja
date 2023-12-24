package com.example.rekrutacja.entity.users;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@SuperBuilder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Candidate extends AppUser {

    private LocalDate birthDate;

}
