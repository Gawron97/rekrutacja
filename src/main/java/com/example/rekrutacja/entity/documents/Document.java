package com.example.rekrutacja.entity.documents;

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
public class Document {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime addDate;
    @ManyToOne
    @JoinColumn(name = "id_app_user")
    private AppUser appUser;

}
