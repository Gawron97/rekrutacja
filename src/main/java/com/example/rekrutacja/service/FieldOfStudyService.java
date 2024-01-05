package com.example.rekrutacja.service;

import com.example.rekrutacja.entity.faculty.FieldOfStudy;
import com.example.rekrutacja.repository.FieldOfStudyRepository;
import com.example.rekrutacja.utils.exception.FieldOfStudyNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FieldOfStudyService {

    private final FieldOfStudyRepository fieldOfStudyRepository;

    public List<String> getFieldOfStudiesNames() {
        return fieldOfStudyRepository.findAll().stream().map(FieldOfStudy::getName).toList();
    }

    public FieldOfStudy getFieldOfStudyById(Long id) {
        return fieldOfStudyRepository.findById(id).orElseThrow(
                () -> new FieldOfStudyNotFoundException("Field of study with id " + id + " not found")
        );
    }
}
