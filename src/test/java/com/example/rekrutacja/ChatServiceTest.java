package com.example.rekrutacja;

import com.example.rekrutacja.entity.AppUserRole;
import com.example.rekrutacja.entity.users.AppUser;
import com.example.rekrutacja.repository.AppUserRepository;
import com.example.rekrutacja.repository.MessageRepository;
import com.example.rekrutacja.service.ChatService;
import com.example.rekrutacja.utils.exception.BadActionException;
import com.example.rekrutacja.utils.exception.ResourceNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@DisplayName("ChatService methods should")
public class ChatServiceTest {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private ChatService chatService;

    private AppUser candidate;
    private AppUser administration;

    @BeforeEach
    void setUp() {
        candidate = appUserRepository.save(
                AppUser.builder()
                        .login("user1")
                        .password("password")
                        .email("email")
                        .pesel("123123")
                        .role(AppUserRole.CANDIDATE)
                        .surname("surname")
                        .name("name")
                        .build()
        );

        administration = appUserRepository.save(
                AppUser.builder()
                        .login("user2")
                        .password("password")
                        .email("email")
                        .pesel("123123")
                        .role(AppUserRole.ADMINISTRATION_EMPLOYEE)
                        .surname("surname")
                        .name("name")
                        .build()
        );
    }

    @AfterEach
    void tearDown() {
        messageRepository.deleteAll();
        appUserRepository.deleteAll();
    }

    @Test
    void sendMessageToUser_success() {
        var message = "message body";
        chatService.sendMessageToUser(candidate.getId(), administration.getLogin(), message);

        var messageFromDb = messageRepository.findAll().get(0);
        assertEquals(message, messageFromDb.getContent());
        assertNotNull(messageFromDb.getSender());
        assertNotNull(messageFromDb.getReceiver());
        assertNotNull(messageFromDb.getCreatedAt());
        assertNotNull(messageFromDb.getId());
        assertNotNull(messageFromDb.getCreatedAt());
    }

    @Test
    void sendMessageToSelf_shouldThrow() {
        assertThrows(
                BadActionException.class,
                () -> chatService.sendMessageToUser(candidate.getId(), candidate.getLogin(), "")
        );
    }

    @Test
    void findUsersMessages_foundAllMessages() {
        assertEquals(0, messageRepository.findAll().size());

        chatService.sendMessageToUser(candidate.getId(), administration.getLogin(), "1");
        chatService.sendMessageToUser(candidate.getId(), administration.getLogin(), "2");
        chatService.sendMessageToUser(administration.getId(), candidate.getLogin(), "3");
        chatService.sendMessageToUser(administration.getId(), candidate.getLogin(), "4");

        var messages = chatService.getMessages(PageRequest.of(0, 10), candidate.getId(), administration.getLogin());
        assertEquals(4, messages.getTotalElements());
    }

    @Test
    void getUserMessages_noUserFound_shouldThrow() {
        assertThrows(
                UsernameNotFoundException.class,
                () -> chatService.getMessages(
                        PageRequest.of(0, 10),
                        candidate.getId(),
                        "not existing user"
                )
        );

        assertThrows(
                ResourceNotFoundException.class,
                () -> chatService.getMessages(
                        PageRequest.of(0, 10),
                        -1L,
                        candidate.getUsername()
                )
        );
    }
}
