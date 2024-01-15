package com.example.rekrutacja.entity.users;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@SuperBuilder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Employee extends AppUser {

    private LocalDate employmentDate;

    private Double salary;

    @Builder.Default
    @Column(columnDefinition = "VARCHAR(30) DEFAULT " + "'" + ActivityStatus.Names.ACTIVE_NAME + "'")
    @Enumerated(value = EnumType.STRING)
    private ActivityStatus activityStatus = ActivityStatus.ACTIVE;
}
