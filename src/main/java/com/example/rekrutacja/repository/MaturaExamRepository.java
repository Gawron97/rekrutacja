package com.example.rekrutacja.repository;

import com.example.rekrutacja.entity.documents.MaturaExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MaturaExamRepository extends JpaRepository<MaturaExam, Long> {

    @Query("SELECT me " +
            "FROM MaturaExam me " +
            "WHERE me.candidate.id = :candidateId")
    Optional<MaturaExam> findByCandidateId(Long candidateId);

    @Query("SELECT COUNT(me) > 0 " +
            "FROM MaturaExam me " +
            "WHERE me.candidate.id = :candidateId AND me.documentStatus = 'APPROVED'")
    Boolean checkIfAllDocumentsAreApprovedForCandidate(Long candidateId);

}
