package com.example.rekrutacja.service.mapper;

import com.example.rekrutacja.DTO.MessageDTO;
import com.example.rekrutacja.entity.chat.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MessageMapper {

    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    @Mapping(source = "message.receiver.id", target = "receiverId")
    @Mapping(source = "message.sender.id", target = "senderId")
    MessageDTO mapToMessageDTO(Message message);
}
