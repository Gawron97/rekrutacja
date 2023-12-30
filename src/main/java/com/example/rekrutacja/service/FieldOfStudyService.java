package com.example.rekrutacja.service;

import com.example.rekrutacja.entity.faculty.FieldOfStudy;
import com.example.rekrutacja.repository.FieldOfStudyRepository;
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

}
