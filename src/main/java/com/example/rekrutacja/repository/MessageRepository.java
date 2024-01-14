package com.example.rekrutacja.repository;

import com.example.rekrutacja.entity.chat.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("""
            SELECT m
            FROM Message m
            WHERE (m.sender.id = ?1 AND m.receiver.id = ?2) OR (m.sender.id = ?2 AND m.receiver.id = ?1)
            """)
    Page<Message> getMessageOfUsers(Long firstUser, Long secondUser, PageRequest pageable);
}
