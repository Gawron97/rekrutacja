package com.example.rekrutacja.DTO;

import com.example.rekrutacja.utils.TimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

public record MessageDTO(
        Long id,
        Long receiverId,
        Long senderId,
        String content,
        @JsonFormat(pattern = TimeFormat.DATE_TIME_FORMAT)
        String createdAt
) {
}
