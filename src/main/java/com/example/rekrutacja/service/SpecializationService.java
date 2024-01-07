package com.example.rekrutacja.service;

import com.example.rekrutacja.DTO.SpecializationDTO;
import com.example.rekrutacja.entity.faculty.Specialization;
import com.example.rekrutacja.repository.SpecializationRepository;
import com.example.rekrutacja.service.mapper.SpecializationMapper;
import com.example.rekrutacja.utils.exception.SpecializationNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpecializationService {

    private final SpecializationRepository specializationRepository;
    private final SpecializationMapper specializationMapper = SpecializationMapper.INSTANCE;

    public Specialization getSpecializationById(Long id) {
        return specializationRepository.findById(id).orElseThrow(
                () -> new SpecializationNotFoundException("Field of study with id " + id + " not found")
        );
    }

    public Page<SpecializationDTO> getSpecializationsOfFieldOfStudy(Pageable pageable, Long fieldOfStudyId) {
        return specializationRepository
                .findAllByFieldOfStudyId(pageable, fieldOfStudyId)
                .map(specializationMapper::mapToSpecializationDTO);
    }
}
