package com.example.rekrutacja.repository;

import com.example.rekrutacja.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
