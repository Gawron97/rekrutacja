package com.example.rekrutacja.controller;

import com.example.rekrutacja.DTO.FieldOfStudyDTO;
import com.example.rekrutacja.DTO.SpecializationDTO;
import com.example.rekrutacja.service.FieldOfStudyService;
import com.example.rekrutacja.service.SpecializationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/field-of-study")
@RequiredArgsConstructor
public class FieldOfStudyController {

    private final FieldOfStudyService fieldOfStudyService;
    private final SpecializationService specializationService;

    @GetMapping
    public Page<FieldOfStudyDTO> getFieldOfStudies(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection
    ) {
        var pageable = PageRequest.of(pageNumber, size, Sort.by(Sort.Direction.fromString(sortDirection), sortBy));
        return fieldOfStudyService.getFieldOfStudies(pageable);
    }

    @GetMapping("/{id}/specialization")
    public Page<SpecializationDTO> getFieldOfStudies(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection,
            @PathVariable Long id
    ) {
        var pageable = PageRequest.of(pageNumber, size, Sort.by(Sort.Direction.fromString(sortDirection), sortBy));
        return specializationService.getSpecializationsOfFieldOfStudy(pageable, id);
    }

}
