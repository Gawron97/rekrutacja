package com.example.rekrutacja.service;

import com.example.rekrutacja.DTO.ApplicationDTO;
import com.example.rekrutacja.DTO.ApplicationInfoDTO;
import com.example.rekrutacja.entity.Recruitment;
import com.example.rekrutacja.entity.documents.*;
import com.example.rekrutacja.entity.faculty.Criteria;
import com.example.rekrutacja.entity.users.Candidate;
import com.example.rekrutacja.repository.*;
import com.example.rekrutacja.utils.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final CandidateRepository candidateRepository;
    private final RecruitmentRepository recruitmentRepository;
    private final MaturaExamRepository maturaExamRepository;
    private final FieldOfStudyRepository fieldOfStudyRepository;

    public ApplicationDTO addApplication(ApplicationDTO application, String appUserLogin) {

        Candidate candidate = getCandidateByLogin(appUserLogin);
        Recruitment recruitment = getRecruitmentByFieldOfStudyName(application.getFieldOfStudy());
        validateApprovedDocuments(candidate.getId());
        Set<PassingSubject> passedSubjects =
                getPassedSubjectsAndValidateCriteria(recruitment.getFieldOfStudy().getId(), candidate.getId());
        checkApplicationDuplicationForRecruitment(recruitment);

        Application newApplication = Application.builder()
                .recruitmentIndicator(
                        calculateRecruitmentIndicator(
                                recruitment.getFieldOfStudy().getRecruitmentRateTemplate(), passedSubjects))
                .preferencesNumber(application.getPreferencesNumber())
                .applicationStatus(ApplicationStatus.SANDED)
                .recruitment(recruitment)
                .candidate(candidate)
                .addDate(LocalDateTime.now())
                .build();

        Application savedApplication = applicationRepository.save(newApplication);
        return ApplicationDTO.of(savedApplication);

    }

    private void checkApplicationDuplicationForRecruitment(Recruitment recruitment) {
        if(applicationRepository.existsByRecruitment(recruitment)) {
            throw new ApplicationDuplicationException();
        }
    }

    private Double calculateRecruitmentIndicator(String recruitmentRateTemplate, Set<PassingSubject> passedSubjects) {
        return 10.0;
    }

    private void validateApprovedDocuments(Long candidateId) {
        if(maturaExamRepository.checkIfAllDocumentsAreApprovedForCandidate(candidateId)) {
            throw new RuntimeException();
        }
    }

    private Set<PassingSubject> getPassedSubjectsAndValidateCriteria(Long fieldOfStudyId, Long candidateId) {
        List<Criteria> criterias = getCriterias(fieldOfStudyId);
        Set<PassingSubject> passedSubjects;
        try {
            passedSubjects = getPassedSubjectsNames(candidateId);
        } catch (MaturaExamNotFound e) {
            throw new CriteriaNotAchievedException(MessageFormat.format("Required criteria for" +
                    " field of study with id: {0} not achieved", fieldOfStudyId));
        }
        Set<String> passedSubjectsName = passedSubjects.stream().map(PassingSubject::getName).collect(Collectors.toSet());

        criterias.forEach(criteria -> {
            if(!passedSubjectsName.contains(criteria.getName())) {
                throw new CriteriaNotAchievedException(MessageFormat.format("Required criteria for" +
                        " field of study with id: {0} not achieved", fieldOfStudyId));
            }
        });
        return passedSubjects;
    }

    private Set<PassingSubject> getPassedSubjectsNames(Long candidateId) {
        return getCandidateMaturaExam(candidateId)
                .getPassingSubjects()
                .stream()
                .filter(passingSubject -> passingSubject.getResult() >= 30.0)
                .collect(Collectors.toSet());
    }

    private MaturaExam getCandidateMaturaExam(Long candidateId) {
        return maturaExamRepository.findAllByCandidateId(candidateId).orElseThrow(MaturaExamNotFound::new);
    }

    private List<Criteria> getCriterias(Long fieldOfStudyId) {
        return fieldOfStudyRepository.findAllCriteriaForFieldOfStudy(fieldOfStudyId);
    }


    private Recruitment getRecruitmentByFieldOfStudyName(String name) {
        return recruitmentRepository.findRecruitmentByFieldOfStudy_Name(name)
                .orElseThrow(RecruitmentNotFoundException::new);
    }

    private Document createDocument(Candidate candidate) {
        return Document.builder()
                .candidate(candidate)
                .addDate(LocalDateTime.now())
                .build();
    }

    private Candidate getCandidateByLogin(String login) {
        return candidateRepository.findByLogin(login).orElseThrow(CandidateNotFoundException::new);
    }

    public List<ApplicationInfoDTO> getApplications() {
        return applicationRepository.findAll().stream().map(ApplicationInfoDTO::of).toList();
    }

    public ApplicationInfoDTO getApplicationDetails(Long id) {
        return ApplicationInfoDTO.of(getApplicationById(id));
    }

    private Application getApplicationById(Long id) {
        return applicationRepository.findById(id).orElseThrow(ApplicationNotFoundException::new);
    }

}
