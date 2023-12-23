package com.example.rekrutacja.repository;

import com.example.rekrutacja.entity.documents.MaturaExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MaturaExamRepository extends JpaRepository<MaturaExam, Long> {

    @Query("SELECT me " +
            "FROM MaturaExam me " +
            "JOIN me.document d " +
            "JOIN d.candidate c " +
            "JOIN me.passingSubjects ps " +
            "WHERE c.id = :candidateId")
    Optional<MaturaExam> findAllByCandidateId(Long candidateId);

    @Query("SELECT COUNT(me) = 0 " +
            "FROM MaturaExam me " +
            "JOIN me.document d " +
            "JOIN d.candidate c " +
            "WHERE c.id = :candidateId AND me.documentStatus = 'UNAPPROVED' ")
    Boolean checkIfAllDocumentsAreApprovedForCandidate(Long candidateId);

}
