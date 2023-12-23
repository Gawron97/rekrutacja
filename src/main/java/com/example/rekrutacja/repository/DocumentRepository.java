package com.example.rekrutacja.repository;

import com.example.rekrutacja.entity.documents.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
