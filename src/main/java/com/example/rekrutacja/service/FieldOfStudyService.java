package com.example.rekrutacja.service;

import com.example.rekrutacja.DTO.FieldOfStudyDTO;
import com.example.rekrutacja.entity.faculty.FieldOfStudy;
import com.example.rekrutacja.repository.FieldOfStudyRepository;
import com.example.rekrutacja.service.mapper.FieldOfStudyMapper;
import com.example.rekrutacja.utils.exception.FieldOfStudyNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FieldOfStudyService {

    private final FieldOfStudyRepository fieldOfStudyRepository;
    private final FieldOfStudyMapper fieldOfStudyMapper = FieldOfStudyMapper.INSTANCE;

    public FieldOfStudy getFieldOfStudyById(Long id) {
        return fieldOfStudyRepository.findById(id).orElseThrow(
                () -> new FieldOfStudyNotFoundException("Field of study with id " + id + " not found")
        );
    }

    public Page<FieldOfStudyDTO> getFieldOfStudies(PageRequest pageable) {
        return fieldOfStudyRepository.findAll(pageable).map(fieldOfStudyMapper::mapToFieldOfStudyDTO);
    }
}
