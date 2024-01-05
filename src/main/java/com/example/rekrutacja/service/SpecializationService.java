package com.example.rekrutacja.service;

import com.example.rekrutacja.entity.faculty.Specialization;
import com.example.rekrutacja.repository.SpecializationRepository;
import com.example.rekrutacja.utils.exception.SpecializationNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpecializationService {

    private final SpecializationRepository specializationRepository;

    public Specialization getSpecializationById(Long id) {
        return specializationRepository.findById(id).orElseThrow(
                () -> new SpecializationNotFoundException("Field of study with id " + id + " not found")
        );
    }
}
