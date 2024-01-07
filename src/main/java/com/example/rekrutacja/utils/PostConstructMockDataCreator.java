package com.example.rekrutacja.utils;

import com.example.rekrutacja.entity.AppUserRole;
import com.example.rekrutacja.entity.Recruitment;
import com.example.rekrutacja.entity.documents.*;
import com.example.rekrutacja.entity.faculty.*;
import com.example.rekrutacja.entity.users.AppUser;
import com.example.rekrutacja.entity.users.Candidate;
import com.example.rekrutacja.entity.users.Employee;
import com.example.rekrutacja.repository.*;
import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
@Slf4j
@Profile("dev")
@RequiredArgsConstructor
public class PostConstructMockDataCreator {

    private final Faker faker = new Faker();
    private final PasswordEncoder passwordEncoder;

    private final AppUserRepository userRepository;
    private final ApplicationRepository applicationRepository;
    private final CandidateRepository candidateRepository;
    private final CertificateRepository certificateRepository;
    private final CriteriaRepository criteriaRepository;
    private final DiplomaOfStudiesRepository diplomaOfStudiesRepository;
    private final EmployeeRepository employeeRepository;
    private final FieldOfStudyRepository fieldOfStudyRepository;
    private final MaturaExamRepository maturaExamRepository;
    private final RecruitmentRepository recruitmentRepository;
    private final SpecializationRepository specializationRepository;

    private final List<String> fieldOfStudyNames = List.of("Informatyka", "Matematyka", "Medycyna");
    private final List<String> specialisationNames = List.of("Robotyka", "AI", "Design");
    private final List<String> passingSubjectsNames = List.of("Matematyka", "Geografia", "Polski");

    @PostConstruct
    public void init() {
        if(userRepository.count() > 0)
            return;

        log.info("Creating mock data in database...");
        createUsers();
        generateData();
        log.info("Mock data successfully created!");
    }

    private void createUsers() {
        var admin = createUserWithProperties(
                "admin",
                "pass",
                AppUserRole.ADMIN
        );
        var user = createUserWithProperties(
                "candidate",
                "pass",
                AppUserRole.CANDIDATE
        );
        var administration = createUserWithProperties(
                "administration",
                "pass",
                AppUserRole.ADMINISTRATION_EMPLOYEE
        );

        userRepository.save(admin);
        userRepository.save(user);
        userRepository.save(administration);
    }

    private AppUser createUserWithProperties(String username, String password, AppUserRole role) {
        return AppUser.builder()
                .name("name")
                .surname("surname")
                .login(username)
                .password(passwordEncoder.encode(password))
                .role(role)
                .pesel("12345678901")
                .email(username + "@example.com")
                .build();
    }

    public void generateData() {
        generateEmployees();
        generateCandidates();
        generateCriteria();
        generateMaturaExam(5);
        generateDiplomaOfStudy(5);
        generateCertificate(5);
        List<Criteria> criteria = criteriaRepository.findAll();
        generateFieldOfStudiesWithSpecializations(criteria);
        List<FieldOfStudy> fieldOfStudies = fieldOfStudyRepository.findAll();
        generateRecruitments(4, fieldOfStudies);
        List<Recruitment> recruitments = recruitmentRepository.findAll();
        generateApplications(10, recruitments);
    }

    private LocalDate getRandomDateBetween(LocalDate minDate, LocalDate maxDate) {
        long minDay = minDate.toEpochDay();
        long maxDay = maxDate.toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay + 1, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    private void generateEmployees() {
        Employee employee1 = Employee.builder()
                .name(faker.name().firstName())
                .surname(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .password(passwordEncoder.encode("1234"))
                .login("emp1")
                .role(AppUserRole.ADMINISTRATION_EMPLOYEE)
                .isEnabled(true)
                .build();

        employeeRepository.save(employee1);

        Employee employee2 = Employee.builder()
                .name(faker.name().firstName())
                .surname(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .password(passwordEncoder.encode("1234"))
                .login("admin1")
                .role(AppUserRole.ADMIN)
                .isEnabled(true)
                .build();

        employeeRepository.save(employee2);

    }

    private void generateCandidates() {

        Candidate candidate1 = Candidate.builder()
                .name(faker.name().firstName())
                .surname(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .password(passwordEncoder.encode("1234"))
                .login("user1")
                .role(AppUserRole.CANDIDATE)
                .isEnabled(true)
                .build();

        candidateRepository.save(candidate1);

        Candidate candidate2 = Candidate.builder()
                .name(faker.name().firstName())
                .surname(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .password(passwordEncoder.encode("1234"))
                .login("user2")
                .role(AppUserRole.CANDIDATE)
                .isEnabled(true)
                .build();

        candidateRepository.save(candidate2);

    }

    private void generateCriteria() {
        passingSubjectsNames.forEach(passingSubjectName -> {
            Criteria criteria = Criteria.builder()
                    .description(faker.lorem().sentence())
                    .name(passingSubjectName)
                    .build();
            criteriaRepository.save(criteria);
        });
    }

    private PassingSubject createPassingSubject() {

        return PassingSubject.builder()
                .name(passingSubjectsNames.get(faker.number().numberBetween(0, passingSubjectsNames.size())))
                .result(faker.number().randomDouble(2, 0, 101))
                .build();

    }

    private void generateMaturaExam(int quantity) {
        for(int i=0; i<quantity; i++) {
            MaturaExam maturaExam = MaturaExam.builder()
                    .documentStatus(DocumentStatus.APPROVED)
                    .passingSubjects(new HashSet<>())
                    .build();

            for(int j=0; j<3; j++) {
                PassingSubject passingSubject = createPassingSubject();
                maturaExam.addPassingSubject(passingSubject);
            }
            maturaExamRepository.save(maturaExam);
        }
    }

    private void generateDiplomaOfStudy(int quantity) {
        for(int i=0; i<quantity; i++) {
            DiplomaOfStudies diplomaOfStudies = DiplomaOfStudies.builder()
                    .grade(faker.number().randomDouble(2, 10, 40))
                    .fieldOfStudyName(fieldOfStudyNames.get(faker.number().numberBetween(0, fieldOfStudyNames.size())))
                    .endDate(getRandomDateBetween(LocalDate.of(2019, 01, 01),
                            LocalDate.of(2023, 01, 01)))
                    .degreeOfStudy(DegreeOfStudy.ENGINEERING)
                    .documentStatus(DocumentStatus.APPROVED)
                    .build();

            diplomaOfStudiesRepository.save(diplomaOfStudies);
        }
    }

    private void generateCertificate(int quantity) {
        for(int i=0; i<quantity; i++) {
            Certificate certificate = Certificate.builder()
                    .certificateType(CertificateType.STUDIUM_TALENTS)
                    .result(faker.number().randomDouble(2, 10, 40))
                    .validityDate(getRandomDateBetween(LocalDate.of(2022, 01, 01),
                            LocalDate.of(2025, 01, 01)))
                    .documentStatus(DocumentStatus.APPROVED)
                    .build();

            certificateRepository.save(certificate);
        }
    }

    private void generateFieldOfStudiesWithSpecializations(List<Criteria> criterias) {
        for (String fieldOfStudyName : fieldOfStudyNames) {
            Specialization spec1 = createRandomSpecialization();
            Specialization spec2 = createRandomSpecialization();
            Specialization spec3 = createRandomSpecialization();

            FieldOfStudy fieldOfStudy = FieldOfStudy.builder()
                    .name(fieldOfStudyName)
                    .studyMode(StudyMode.FULL_TIME)
                    .degreeOfStudy(DegreeOfStudy.ENGINEERING)
                    .criterias(new HashSet<>())
                    .build();

            for (int i = 0; i < 3; i++) {
                fieldOfStudy.addCriteria(criterias.get(faker.number().numberBetween(0, criterias.size())));
            }

            fieldOfStudyRepository.save(fieldOfStudy);

            spec1.setFieldOfStudy(fieldOfStudy);
            spec2.setFieldOfStudy(fieldOfStudy);
            spec3.setFieldOfStudy(fieldOfStudy);

            specializationRepository.save(spec1);
            specializationRepository.save(spec2);
            specializationRepository.save(spec3);
        }
    }

    private Specialization createRandomSpecialization() {
        return Specialization.builder()
                .name("Specjalizacja " + faker.lorem().word())
                .build();
    }


    private void generateRecruitments(int quantity, List<FieldOfStudy> fieldOfStudies) {
        for(int i=0; i<quantity; i++) {
            Recruitment recruitment = Recruitment.builder()
                    .cycle("2023/2024")
                    .startDate(LocalDate.now().minusDays(faker.number().numberBetween(1, 30)))
                    .endDate(LocalDate.now().plusDays(faker.number().numberBetween(1, 30)))
                    .capacity(faker.number().numberBetween(1, 150))
                    .thresholdPoints(faker.number().randomDouble(2, 10, 100))
                    .fieldOfStudy(fieldOfStudies.get(faker.number().numberBetween(0, fieldOfStudies.size())))
                    .build();

            recruitmentRepository.save(recruitment);
        }
    }

    private void generateSpecializations() {
        specialisationNames.forEach(specialisationName -> {
            Specialization specialization = Specialization.builder()
                    .name(specialisationName)
                    .build();
            specializationRepository.save(specialization);
        });
    }

    private void generateApplications(int quantity, List<Recruitment> recruitments) {
        for(int i=0; i<quantity; i++) {
            Application application = Application.builder()
                    .recruitmentIndicator(faker.number().randomDouble(2, 0, 100))
                    .preferencesNumber(faker.number().numberBetween(1, 10))
                    .applicationStatus(Arrays.stream(ApplicationStatus.values())
                            .toList().get(faker.number().numberBetween(0, ApplicationStatus.values().length)))
                    .recruitment(recruitments.get(faker.number().numberBetween(0, recruitments.size())))
                    .build();
            applicationRepository.save(application);
        }
    }
}
