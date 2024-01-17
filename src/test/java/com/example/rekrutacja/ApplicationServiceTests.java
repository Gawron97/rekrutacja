package com.example.rekrutacja;

import com.example.rekrutacja.DTO.ApplicationDTO;
import com.example.rekrutacja.DTO.ApplicationInfoDTO;
import com.example.rekrutacja.entity.Recruitment;
import com.example.rekrutacja.entity.documents.Application;
import com.example.rekrutacja.entity.documents.ApplicationStatus;
import com.example.rekrutacja.entity.documents.DocumentStatus;
import com.example.rekrutacja.entity.documents.MaturaExam;
import com.example.rekrutacja.entity.faculty.FieldOfStudy;
import com.example.rekrutacja.entity.users.Candidate;
import com.example.rekrutacja.repository.*;
import com.example.rekrutacja.service.ApplicationService;
import com.example.rekrutacja.utils.exception.DocumentsUnapprovedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApplicationServiceTests {

    private ApplicationRepository applicationRepository;
    private CandidateRepository candidateRepository;
    private RecruitmentRepository recruitmentRepository;
    private MaturaExamRepository maturaExamRepository;
    private FieldOfStudyRepository fieldOfStudyRepository;
    private ApplicationService applicationService;

    @BeforeEach
    public void prepare() {
        applicationRepository = mock(ApplicationRepository.class);
        candidateRepository = mock(CandidateRepository.class);
        recruitmentRepository = mock(RecruitmentRepository.class);
        maturaExamRepository = mock(MaturaExamRepository.class);
        fieldOfStudyRepository = mock(FieldOfStudyRepository.class);
        applicationService = new ApplicationService(applicationRepository, candidateRepository, recruitmentRepository,
                maturaExamRepository, fieldOfStudyRepository);
    }

    private Candidate getSampleCandidate() {
        return Candidate.builder()
                .id(1L)
                .login("user")
                .name("user")
                .password("user")
                .isEnabled(true)
                .build();

    }

    private Recruitment getSampleRecruitment() {
        return Recruitment.builder()
                .id(1L)
                .fieldOfStudy(getSampleFieldOfStudy())
                .build();
    }

    private FieldOfStudy getSampleFieldOfStudy() {
        return FieldOfStudy.builder()
                .id(1L)
                .name("Informatyka")
                .build();
    }

    private MaturaExam getSampleMaturaExam() {
        return MaturaExam.builder()
                .id(1L)
                .candidate(getSampleCandidate())
                .build();
    }

    private Application getSampleApplication() {
        return Application.builder()
                .id(1L)
                .candidate(getSampleCandidate())
                .recruitmentIndicator(12.0)
                .recruitment(getSampleRecruitment())
                .preferencesNumber(0)
                .applicationStatus(ApplicationStatus.SANDED)
                .build();
    }

    @Test
    public void givenSomeApplications_whenGetAllApplications_thenReturnAllApplications() {

        // given
        List<Application> applications = List.of(
                getSampleApplication(),
                getSampleApplication(),
                getSampleApplication()
        );

        Page<Application> applicationPage = new PageImpl<>(applications);
        PageRequest pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.fromString("ASC"), "id"));

        // when
        when(applicationRepository.findAll(pageable)).thenReturn(applicationPage);
        Page<ApplicationInfoDTO> takenApplications =
                applicationService.getApplications(pageable);


        // then
        assertEquals(3, takenApplications.getTotalElements());

    }

    @Test
    public void givenNewApplication_whenCandidateDocumentUnapproved_thenThrowDocumentsUnapprovedException() {

        // given
        ApplicationDTO applicationDTO = ApplicationDTO.builder()
                .fieldOfStudy("Informatyka")
                .preferencesNumber(0)
                .build();

        // when
        MaturaExam maturaExam = getSampleMaturaExam();
        maturaExam.setDocumentStatus(DocumentStatus.UNAPPROVED);

        when(candidateRepository.findByLogin("user")).thenReturn(Optional.of(getSampleCandidate()));
        when(recruitmentRepository.findRecruitmentByFieldOfStudy_Name("Informatyka")).thenReturn(Optional.of(getSampleRecruitment()));
        when(maturaExamRepository.findByCandidateId(1L)).thenReturn(Optional.of(maturaExam));

        // then
        assertThrows(DocumentsUnapprovedException.class, () -> applicationService.addApplication(applicationDTO, "user"));

    }

}
