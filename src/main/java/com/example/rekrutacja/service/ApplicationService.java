package com.example.rekrutacja.service;

import com.example.rekrutacja.DTO.ApplicationDTO;
import com.example.rekrutacja.entity.Recruitment;
import com.example.rekrutacja.entity.documents.Application;
import com.example.rekrutacja.entity.documents.ApplicationStatus;
import com.example.rekrutacja.entity.documents.Document;
import com.example.rekrutacja.entity.users.Candidate;
import com.example.rekrutacja.repository.ApplicationRepository;
import com.example.rekrutacja.repository.CandidateRepository;
import com.example.rekrutacja.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final CandidateRepository candidateRepository;
    private final RecruitmentRepository recruitmentRepository;

    public ApplicationDTO addApplication(ApplicationDTO application, String appUserLogin) {

        Candidate candidate = getCandidateByLogin(appUserLogin);
        Recruitment recruitment = getRecruitmentById(application.getRecruitmentId());

        //kryteria mozna wyciagnac dla kierunku i jesli kryterium przyjmiemy jako nazwe przedmiotu ktory musi byc zdany
        //na maturze to mozna przejsc po maturach i zobaczyc czy istnieje taki dokument z takim zdanym przedmiotem
        //jesli nie spelnione to rzucic wyjatek nie spelnienia kryteriow, jesli spelnione metoda w sumie od razu moze
        //zwrocic liste przedmiotow zdawanych i wynik, co moze byc uzyte do obliczenia wskaznika

        Application newApplication = Application.builder()
                .recruitmentIndicator(10.0) // wyciagnac wyniki matury z dokumentow kandydata i policzyc wskaznik
                .preferencesNumber(application.getPreferencesNumber())
                .applicationStatus(ApplicationStatus.SANDED)
                .recruitment(recruitment)
                .document(createDocument(candidate))
                .build();

        Application savedApplication = applicationRepository.save(newApplication);
        return ApplicationDTO.of(savedApplication);

    }

    private Recruitment getRecruitmentById(Long id) {
        return recruitmentRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    private Document createDocument(Candidate candidate) {
        return Document.builder()
                .candidate(candidate)
                .addDate(LocalDateTime.now())
                .build();
    }

    private Candidate getCandidateByLogin(String login) {
        return candidateRepository.findByAppUser_Login(login).orElseThrow(RuntimeException::new);
    }

}
