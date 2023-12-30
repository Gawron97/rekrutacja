package com.example.rekrutacja.repository;

import com.example.rekrutacja.entity.documents.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {
}
