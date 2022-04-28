package com.albo.registroconsignatario.repository;

import com.albo.registroconsignatario.model.DocumentType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDocumentTypeRepository extends JpaRepository<DocumentType, Long> {
    
}
