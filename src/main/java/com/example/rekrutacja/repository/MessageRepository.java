package com.example.rekrutacja.repository;

import com.example.rekrutacja.DTO.ChatParticipantDTO;
import com.example.rekrutacja.entity.chat.Message;
import com.example.rekrutacja.entity.users.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("""
            SELECT m
            FROM Message m
            WHERE (m.sender.id = ?1 AND m.receiver.id = ?2) OR (m.sender.id = ?2 AND m.receiver.id = ?1)
            """)
    Page<Message> getMessageOfUsers(Long firstUserId, Long secondUserId, PageRequest pageable);

    @Query("""
        SELECT DISTINCT u
        FROM AppUser u
        WHERE u.id IN (
            (SELECT DISTINCT m.sender.id
            FROM Message m
            WHERE m.receiver.id = ?1)
            UNION
            (SELECT DISTINCT m.receiver.id
            FROM Message m
            WHERE m.sender.id = ?1)
        )
        """)
    Page<AppUser> getUsersChattingWith(Long userId, Pageable pageable);
}
