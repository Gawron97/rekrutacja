package com.example.rekrutacja.service.mapper;

import com.example.rekrutacja.DTO.recruitment.RecruitmentDTO;
import com.example.rekrutacja.DTO.recruitment.RecruitmentRequest;
import com.example.rekrutacja.entity.Recruitment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RecruitmentMapper {

    RecruitmentMapper INSTANCE = Mappers.getMapper(RecruitmentMapper.class);

    RecruitmentDTO mapToRecruitmentDTO(Recruitment recruitmentMapperDTO);

    void updateNonEntityFields(@MappingTarget Recruitment recruitment, RecruitmentRequest newData);

    Recruitment mapNonEntityFieldsToRecruitment(RecruitmentRequest recruitmentDTO);
}
