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
import java.util.Map;
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
        checkApplicationDuplicationForRecruitment(recruitment, candidate);

        Application newApplication = Application.builder()
                .recruitmentIndicator(
                        calculateRecruitmentIndicator(
                                recruitment.getFieldOfStudy().getRecruitmentRateTemplate(), getMap(passedSubjects)))
                .preferencesNumber(application.getPreferencesNumber())
                .applicationStatus(ApplicationStatus.SANDED)
                .recruitment(recruitment)
                .candidate(candidate)
                .addDate(LocalDateTime.now())
                .build();

        Application savedApplication = applicationRepository.save(newApplication);
        return ApplicationDTO.of(savedApplication);

    }

    private Map<String, Double> getMap(Set<PassingSubject> passingSubjects) {

        return passingSubjects.stream().map(passingSubject ->
                Map.entry(passingSubject.getName().toLowerCase(), passingSubject.getResult()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    }

    private void checkApplicationDuplicationForRecruitment(Recruitment recruitment, Candidate candidate) {
        if(applicationRepository.existsByRecruitmentAndCandidate(recruitment, candidate)) {
            throw new ApplicationDuplicationException();
        }
    }

    private Double calculateRecruitmentIndicator(String recruitmentRateTemplate, Map<String, Double> passedSubjects) {

        Double result = 0.0;
        String[] tokens = recruitmentRateTemplate.split("\\+");

        for(String token : tokens) {
            if(token.contains("*")) {
                String[] subTokens = token.split("\\*");
                result += passedSubjects.get(subTokens[0]) * Double.parseDouble(subTokens[1]);
            } else {
                result += passedSubjects.get(token);
            }
        }

        return result;
    }

    private void validateApprovedDocuments(Long candidateId) {
        MaturaExam candidateMaturaExam = getCandidateMaturaExam(candidateId);
        if(candidateMaturaExam.getDocumentStatus().equals(DocumentStatus.UNAPPROVED)) {
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
                    " field of study with id: {0} not achieved, candidate did not send matura exam", fieldOfStudyId));
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
        return maturaExamRepository.findByCandidateId(candidateId).orElseThrow(MaturaExamNotFound::new);
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

    public List<Integer> getPreferences(String name) {
        return applicationRepository.findPreferencesNumberByCandidate(getCandidateByLogin(name));
    }
}
