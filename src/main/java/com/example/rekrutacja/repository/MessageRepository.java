package com.example.rekrutacja.repository;

import com.example.rekrutacja.entity.chat.Message;
import com.example.rekrutacja.entity.users.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Modifying
    @Query("""
            DELETE FROM Message m
            WHERE (m.sender.id = ?1 AND m.receiver.id = ?2) OR (m.sender.id = ?2 AND m.receiver.id = ?1)
            """)
    void deleteConversation(Long userId, Long secondUserId);

    @Modifying
    @Query(value = """
                    DELETE FROM Message
                    WHERE id_sender IN 
                        (SELECT id FROM app_user WHERE login = ?1) OR id_receiver IN (SELECT id FROM app_user WHERE login = ?1)
                    """,
            nativeQuery = true)
    void deleteAllMessagesOfUser(String username);
}
