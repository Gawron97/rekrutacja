package com.example.rekrutacja;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RekrutacjaApplication implements CommandLineRunner {

//    @Autowired
//    MaturaExamRepository maturaExamRepository;
//
//    @Autowired
//    DocumentRepository documentRepository;
//
//    @Autowired
//    AppUserRepository appUserRepository;
//
//    @Autowired
//    CandidateRepository candidateRepository;
//
//    @Autowired
//    ApplicationRepository applicationRepository;
    public static void main(String[] args) {
        SpringApplication.run(RekrutacjaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

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
