package com.example.rekrutacja.entity.users;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime lastActivity = LocalDateTime.now();

    @Builder.Default
    @Column(columnDefinition = "VARCHAR(30) DEFAULT " + "'" + ActivityStatus.Names.INACTIVE_NAME + "'")
    @Enumerated(value = EnumType.STRING)
    private ActivityStatus activityStatus = ActivityStatus.INACTIVE;
}
