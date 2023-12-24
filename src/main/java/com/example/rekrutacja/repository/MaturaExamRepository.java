package com.example.rekrutacja.repository;

import com.example.rekrutacja.entity.documents.MaturaExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MaturaExamRepository extends JpaRepository<MaturaExam, Long> {

    @Query("SELECT me " +
            "FROM MaturaExam me " +
            "WHERE me.candidate.id = :candidateId")
    Optional<MaturaExam> findAllByCandidateId(Long candidateId);


    @Query("SELECT COUNT(me) = 0 " +
            "FROM MaturaExam me " +
            "WHERE me.candidate.id = :candidateId AND me.documentStatus = 'UNAPPROVED'")
    Boolean checkIfAllDocumentsAreApprovedForCandidate(Long candidateId);

}
