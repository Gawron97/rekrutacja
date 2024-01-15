package com.example.rekrutacja.service;

import com.example.rekrutacja.DTO.MessageDTO;
import com.example.rekrutacja.entity.chat.Message;
import com.example.rekrutacja.entity.users.AppUser;
import com.example.rekrutacja.repository.MessageRepository;
import com.example.rekrutacja.service.mapper.MessageMapper;
import com.example.rekrutacja.utils.exception.BadActionException;
import com.example.rekrutacja.utils.exception.ResourceNotFoundException;
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
        if(!appUserService.existsById(firstUserId))
            throw new ResourceNotFoundException("User with id " + firstUserId + " not found");

        return messageRepository
                .getMessageOfUsers(appUserService.getIdOfUserByUsername(secondUsername), firstUserId, pageable)
                .map(messageMapper::mapToMessageDTO);
    }

    public void sendMessageToUser(Long receiverId, String senderUsername, String content) {
        var sender = appUserService.getUserByUsername(senderUsername);

        if (sender.getId().equals(receiverId))
            throw new BadActionException("Receiver and sender cannot be the same person.");

        var receiver = appUserService.getUserById(receiverId);
        var message = createMessage(receiver, sender, content);
        messageRepository.save(message);
    }

    private Message createMessage(AppUser receiver, AppUser sender, String content) {
        return Message.builder()
                .sender(sender)
                .receiver(receiver)
                .content(content)
                .build();
    }
}
