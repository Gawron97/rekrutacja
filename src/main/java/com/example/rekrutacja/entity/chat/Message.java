package com.example.rekrutacja.entity.chat;

import com.example.rekrutacja.entity.users.AppUser;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Check;

import java.time.LocalDateTime;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Check(constraints = "id_receiver <> id_sender")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    @Column(columnDefinition = "VARCHAR(255) DEFAULT ''", nullable = false, updatable = false)
    private String content = "";

    @Builder.Default
    @Setter(AccessLevel.NONE)
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "id_receiver", nullable = false, updatable = false)
    private AppUser receiver;

    @ManyToOne
    @JoinColumn(name = "id_sender", nullable = false, updatable = false)
    private AppUser sender;

}
