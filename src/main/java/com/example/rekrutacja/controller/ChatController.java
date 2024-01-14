package com.example.rekrutacja.controller;

import com.example.rekrutacja.DTO.MessageContentDTO;
import com.example.rekrutacja.DTO.MessageDTO;
import com.example.rekrutacja.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

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
    public void sendMessageToUser(
            @PathVariable Long userId,
            @RequestBody MessageContentDTO messageContentDTO,
            Principal principal
    ) {
        chatService.sendMessageToUser(userId, principal.getName(), messageContentDTO.content());
    }

}
