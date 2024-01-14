package com.example.rekrutacja.entity.chat;

import com.example.rekrutacja.entity.users.AppUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(255) DEFAULT ''", nullable = false, updatable = false)
    private String content;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "id_receiver", nullable = false, updatable = false)
    private AppUser receiver;

    @ManyToOne
    @JoinColumn(name = "id_sender", nullable = false, updatable = false)
    private AppUser sender;

}
