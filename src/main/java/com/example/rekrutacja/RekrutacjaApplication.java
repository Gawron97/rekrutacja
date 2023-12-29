package com.example.rekrutacja;

import com.example.rekrutacja.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RekrutacjaApplication implements CommandLineRunner {

    @Autowired
    MaturaExamRepository maturaExamRepository;

    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    public static void main(String[] args) {
        SpringApplication.run(RekrutacjaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//        Candidate candidate = Candidate.builder()
//                .birthDate(LocalDate.now())
//                .name("Jakub")
//                .role(Role.CANDIDATE)
//                .build();
//
//        Employee employee = Employee.builder()
//                .role(Role.ADMINISTRATION_EMPLOYEE)
//                .name("Jrzy")
//                .salary(10.0)
//                .build();
//
//        candidateRepository.save(candidate);
//        employeeRepository.save(employee);
//
//        MaturaExam maturaExam = MaturaExam.builder()
//                .documentStatus(DocumentStatus.APPROVED)
//                .addDate(LocalDateTime.now())
//                .build();
//
//        Application application = Application.builder()
//                .applicationStatus(ApplicationStatus.SANDED)
//                .preferencesNumber(12)
//                .addDate(LocalDateTime.now())
//                .build();
//
//        documentRepository.save(maturaExam);
//        documentRepository.save(application);

//        AppUser appUser = AppUser.builder()
//                .name("test")
//                .build();
//
//        appUserRepository.save(appUser);
//
//        Candidate candidate = Candidate.builder()
//                .appUser(appUser)
//                .build();
//
//        candidateRepository.save(candidate);
//
//        Document document1 = Document.builder()
//                .candidate(candidate)
//                .addDate(LocalDateTime.now())
//                .build();
//
//        Document document2 = Document.builder()
//                .candidate(candidate)
//                .addDate(LocalDateTime.now())
//                .build();
//
//        MaturaExam maturaExam = MaturaExam.builder()
//                .document(document1)
//                .build();
//
//        maturaExamRepository.save(maturaExam);
//
//        Application application = Application.builder()
//                .document(document2)
//                .build();
//
//        applicationRepository.save(application);
//
//        System.out.println(document1.getMaturaExam());
//        System.out.println(document2.getMaturaExam());

    }
}
