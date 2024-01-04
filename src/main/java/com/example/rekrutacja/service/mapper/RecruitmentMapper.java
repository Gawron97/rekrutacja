package com.example.rekrutacja.service.mapper;

import com.example.rekrutacja.DTO.recruitment.RecruitmentDTO;
import com.example.rekrutacja.entity.Recruitment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RecruitmentMapper {

    RecruitmentMapper INSTANCE = Mappers.getMapper(RecruitmentMapper.class);

    RecruitmentDTO mapToRecruitmentDTO(Recruitment recruitmentMapperDTO);

}
