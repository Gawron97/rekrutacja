package com.example.rekrutacja.service;

import com.example.rekrutacja.DTO.ChatParticipantDTO;
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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final EmployeeService employeeService;
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

    public MessageDTO sendMessageToUser(Long receiverId, String senderUsername, String content) {
        var sender = appUserService.getUserByUsername(senderUsername);

        if (sender.getId().equals(receiverId))
            throw new BadActionException("Receiver and sender cannot be the same person.");

        var receiver = appUserService.getUserById(receiverId);
        var message = createMessage(receiver, sender, content);
        return messageMapper.mapToMessageDTO(messageRepository.save(message));
    }

    public MessageDTO sendMessageToAvailableEmployee(String name, String content) {
        var employee = employeeService.getAvailableEmployee();

        return sendMessageToUser(
                employee.getId(),
                name,
                content
        );
    }

    public Page<ChatParticipantDTO> getUsersChattingWith(Pageable pageable, String username) {
        var userId = appUserService.getIdOfUserByUsername(username);
        return messageRepository
                .getUsersChattingWith(userId, pageable)
                .map(messageMapper::mapToChatParticipantDTO);
    }

    @Transactional
    public void deleteConversation(Long userId, String username) {
        var secondUserId = appUserService.getIdOfUserByUsername(username);
        messageRepository.deleteConversation(userId, secondUserId);
    }

    private Message createMessage(AppUser receiver, AppUser sender, String content) {
        return Message.builder()
                .sender(sender)
                .receiver(receiver)
                .content(content)
                .build();
    }
}
