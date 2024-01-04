package com.example.rekrutacja.service;

import com.example.rekrutacja.DTO.recruitment.RecruitmentDTO;
import com.example.rekrutacja.DTO.recruitment.RecruitmentShortDTO;
import com.example.rekrutacja.entity.Recruitment;
import com.example.rekrutacja.repository.RecruitmentRepository;
import com.example.rekrutacja.service.mapper.RecruitmentMapper;
import com.example.rekrutacja.utils.exception.RecruitmentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;

    private final RecruitmentMapper recruitmentMapper = RecruitmentMapper.INSTANCE;

    /**
     * @param search search by fieldOfStudy name and cycle
     * */
    public Page<RecruitmentShortDTO> getRecruitmentsShort(PageRequest pageable, String search) {
        return recruitmentRepository
                .findRecruitmentByFieldOfStudyOrCycleName(search, pageable)
                .map(r -> new RecruitmentShortDTO(r.getId(), r.getFieldOfStudy().getName() + " " + r.getCycle()));
    }

    public RecruitmentDTO getRecruitmentById(Long id) {
        Recruitment recruitment = recruitmentRepository.findById(id).orElseThrow(
                () -> new RecruitmentNotFoundException("Recruitment with id " + id + " not found")
        );

        return recruitmentMapper.mapToRecruitmentDTO(recruitment);
    }
}
