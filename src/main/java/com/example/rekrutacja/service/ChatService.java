package com.example.rekrutacja.service;

import com.example.rekrutacja.DTO.ChatRequest;
import com.example.rekrutacja.DTO.MessageDTO;
import com.example.rekrutacja.repository.MessageRepository;
import com.example.rekrutacja.service.auth.AppUserService;
import com.example.rekrutacja.service.mapper.MessageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final MessageRepository messageRepository;
    private final AppUserService appUserService;
    private final MessageMapper messageMapper = MessageMapper.INSTANCE;

    public Page<MessageDTO> getMessages(PageRequest pageable, Long firstUserId, String secondUsername) {
        return messageRepository
                .getMessageOfUsers(appUserService.getIdOfUserByUsername(secondUsername), firstUserId, pageable)
                .map(messageMapper::mapToMessageDTO);
    }
}
