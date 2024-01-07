package com.example.rekrutacja.controller;

import com.example.rekrutacja.DTO.recruitment.RecruitmentDTO;
import com.example.rekrutacja.DTO.recruitment.RecruitmentShortDTO;
import com.example.rekrutacja.DTO.recruitment.RecruitmentRequest;
import com.example.rekrutacja.service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recruitment")
@RequiredArgsConstructor
public class RecruitmentController {

    private final RecruitmentService recruitmentService;

    @GetMapping
    public Page<RecruitmentShortDTO> getRecruitments(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection,
            @RequestParam(defaultValue = "") String search
    ) {
        var pageable = PageRequest.of(pageNumber, size, Sort.by(Sort.Direction.fromString(sortDirection), sortBy));
        return recruitmentService.getRecruitmentsShort(pageable, search);
    }

    @GetMapping("/{id}")
    public RecruitmentDTO getRecruitmentById(@PathVariable Long id) {
        return recruitmentService.getRecruitmentDTOById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteRecruitmentById(@PathVariable Long id) {
        recruitmentService.deleteRecruitmentById(id);
    }


    // TODO: if recruitment has specialization, then it has to be selected
    @PutMapping("/{id}")
    public void updateRecruitmentById(
            @PathVariable Long id,
            @RequestBody RecruitmentRequest newData) {
        recruitmentService.updateRecruitmentById(id, newData);
    }

    @PostMapping
    public void createRecruitment(@RequestBody RecruitmentRequest recruitmentDTO) {
        recruitmentService.createRecruitment(recruitmentDTO);
    }
}
