package com.example.rekrutacja.service.mapper;

import com.example.rekrutacja.DTO.SpecializationDTO;
import com.example.rekrutacja.entity.faculty.Specialization;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SpecializationMapper {
    SpecializationMapper INSTANCE = Mappers.getMapper(SpecializationMapper.class);

    SpecializationDTO mapToSpecializationDTO(Specialization specializationMapperDTO);
}
