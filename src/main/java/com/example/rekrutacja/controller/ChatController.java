package com.example.rekrutacja.controller;

import com.example.rekrutacja.DTO.ChatParticipantDTO;
import com.example.rekrutacja.DTO.MessageContentDTO;
import com.example.rekrutacja.DTO.MessageDTO;
import com.example.rekrutacja.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @GetMapping("/chatting-with")
    public Page<ChatParticipantDTO> getReceivers(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection,
            Principal principal
    ) {
        var pageable = PageRequest.of(pageNumber, size, Sort.by(Sort.Direction.fromString(sortDirection), sortBy));
        return chatService.getUsersChattingWith(pageable, principal.getName());
    }

    @GetMapping("/{userId}")
    public Page<MessageDTO> getMessages(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection,
            @PathVariable Long userId,
            Principal principal
    ) {
        var pageable = PageRequest.of(pageNumber, size, Sort.by(Sort.Direction.fromString(sortDirection), sortBy));
        return chatService.getMessages(pageable, userId, principal.getName());
    }

    @PostMapping("/{userId}")
    public MessageDTO sendMessageToUser(
            @PathVariable Long userId,
            @RequestBody MessageContentDTO messageContentDTO,
            Principal principal
    ) {
        return chatService.sendMessageToUser(userId, principal.getName(), messageContentDTO.content());
    }

    @PostMapping("/pick-employee")
    @PreAuthorize("hasRole('ROLE_CANDIDATE')")
    public MessageDTO sendMessageToAvailableEmployee(
            Principal principal,
            @RequestBody MessageContentDTO messageContentDTO
    ) {
        return chatService.deletePreviousConversationsAndSendMessageToEmployee(
                principal.getName(),
                messageContentDTO.content()
        );
    }

    @DeleteMapping("/{userId}")
    public void deleteConversation(
            @PathVariable Long userId,
            Principal principal
    ) {
        chatService.deleteConversation(userId, principal.getName());
    }
}
