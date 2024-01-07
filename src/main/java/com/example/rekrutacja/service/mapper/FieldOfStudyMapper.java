package com.example.rekrutacja.service.mapper;

import com.example.rekrutacja.DTO.FieldOfStudyDTO;
import com.example.rekrutacja.entity.faculty.FieldOfStudy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FieldOfStudyMapper {

    FieldOfStudyMapper INSTANCE = Mappers.getMapper(FieldOfStudyMapper.class);

    FieldOfStudyDTO mapToFieldOfStudyDTO(FieldOfStudy fieldOfStudyMapperDTO);
}
