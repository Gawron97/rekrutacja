package com.example.rekrutacja.service;

import com.example.rekrutacja.DTO.recruitment.RecruitmentDTO;
import com.example.rekrutacja.DTO.recruitment.RecruitmentShortDTO;
import com.example.rekrutacja.DTO.recruitment.RecruitmentRequest;
import com.example.rekrutacja.entity.Recruitment;
import com.example.rekrutacja.entity.faculty.FieldOfStudy;
import com.example.rekrutacja.repository.RecruitmentRepository;
import com.example.rekrutacja.service.mapper.RecruitmentMapper;
import com.example.rekrutacja.utils.exception.FieldRequiredException;
import com.example.rekrutacja.utils.exception.RecruitmentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;
    private final FieldOfStudyService fieldOfStudyService;
    private final SpecializationService specializationService;

    private final RecruitmentMapper recruitmentMapper = RecruitmentMapper.INSTANCE;

    /**
     * @param search search by fieldOfStudy name and cycle
     * */
    public Page<RecruitmentShortDTO> getRecruitmentsShort(Pageable pageable, String search) {
        return recruitmentRepository
                .findRecruitmentsByFieldOfStudyOrCycleOrSpecializationName(search, pageable)
                .map(r -> new RecruitmentShortDTO(r.getId(), createTitle(r)));
    }

    public Recruitment getRecruitmentById(Long id) {
        return recruitmentRepository.findById(id).orElseThrow(
                () -> new RecruitmentNotFoundException("Recruitment with id " + id + " not found")
        );
    }

    public RecruitmentDTO getRecruitmentDTOById(Long id) {
        Recruitment recruitment = getRecruitmentById(id);
        return recruitmentMapper.mapToRecruitmentDTO(recruitment);
    }

    public void deleteRecruitmentById(Long id) {
        checkIfRecruitmentExists(id);
        recruitmentRepository.deleteById(id);
    }

    private void checkIfRecruitmentExists(Long id) {
        if (!recruitmentRepository.existsById(id)) {
            throw new RecruitmentNotFoundException("Recruitment with id " + id + " not found");
        }
    }

    @Transactional
    public void updateRecruitmentById(Long id, RecruitmentRequest newData) {
        Recruitment recruitment = getRecruitmentById(id);

        recruitmentMapper.updateNonEntityFields(recruitment, newData);
        setFieldOfStudyIfIsChanged(recruitment, newData.fieldOfStudyId());
        setSpecializationIfIsChanged(recruitment, newData.specializationId());

        recruitmentRepository.save(recruitment);
    }

    public void createRecruitment(RecruitmentRequest recruitmentDTO) {
        Recruitment recruitment = recruitmentMapper.mapNonEntityFieldsToRecruitment(recruitmentDTO);
        recruitment.setFieldOfStudy(fieldOfStudyService.getFieldOfStudyById(recruitmentDTO.fieldOfStudyId()));

        if(recruitmentDTO.specializationId() != null)
            recruitment.setSpecialization(specializationService.getSpecializationById(recruitmentDTO.specializationId()));

        checkRecruitmentShouldHaveSpecialization(recruitment);
        recruitmentRepository.save(recruitment);
    }

    private void setFieldOfStudyIfIsChanged(Recruitment recruitment, Long fieldOfStudyId) {
        if(!Objects.equals(recruitment.getFieldOfStudy().getId(), fieldOfStudyId))
            recruitment.setFieldOfStudy(fieldOfStudyService.getFieldOfStudyById(fieldOfStudyId));
    }

    private void setSpecializationIfIsChanged(Recruitment recruitment, Long specId) {
        if(recruitment.getSpecialization() != null) {
            if(!Objects.equals(recruitment.getSpecialization().getId(), specId)) {
                recruitment.setSpecialization(specializationService.getSpecializationById(specId));
            }
        } else if(specId != null) {
            recruitment.setSpecialization(specializationService.getSpecializationById(specId));
        }

        checkRecruitmentShouldHaveSpecialization(recruitment);
    }

    private void checkRecruitmentShouldHaveSpecialization(Recruitment recruitment) {
        FieldOfStudy recruitmentFieldOfStudy = recruitment.getFieldOfStudy();

        if(!recruitmentFieldOfStudy.getSpecializations().isEmpty() && recruitment.getSpecialization() == null)
            throw new FieldRequiredException("Field of study has specializations so it cannot be null in recruitment");
    }

    private String createTitle(Recruitment recruitment) {
        return recruitment.getFieldOfStudy().getName() +
                (recruitment.getSpecialization() != null ? " Spec. " + recruitment.getSpecialization().getName() : "") + " " +
                recruitment.getCycle();
    }

    public List<String> getActiveRecruitmentFieldOfStudiesNames() {

        return recruitmentRepository.findFieldOfStudiesForActiveRecruitments()
                .stream()
                .map(FieldOfStudy::getName)
                .toList();

    }
}
