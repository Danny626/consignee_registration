package com.albo.registroconsignatario.impl;

import java.util.List;
import java.util.Optional;

import com.albo.registroconsignatario.model.DocumentType;
import com.albo.registroconsignatario.repository.IDocumentTypeRepository;
import com.albo.registroconsignatario.service.IDocumentTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentTypeServiceImpl implements IDocumentTypeService {

    @Autowired
    private IDocumentTypeRepository documentTypeRepository;

    @Override
    public Boolean deleteById(Long id) {
        if(!documentTypeRepository.existsById(id)) {
            return false;
        }
        documentTypeRepository.deleteById(id);
        return true;
    }

    @Override
    public List<DocumentType> findAll() {
        return documentTypeRepository.findAll();
    }

    @Override
    public Optional<DocumentType> findById(Long id) {
        return documentTypeRepository.findById(id);
    }

    @Override
    public DocumentType saveOrUpdate(DocumentType t) {
        return documentTypeRepository.save(t);
    }
    
}
