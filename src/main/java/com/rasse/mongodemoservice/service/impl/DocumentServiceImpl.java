package com.rasse.mongodemoservice.service.impl;

import com.rasse.mongodemoservice.dao.DocumentRepository;
import com.rasse.mongodemoservice.domen.CustomDocument;
import com.rasse.mongodemoservice.exceptions.DocumentNotFoundException;
import com.rasse.mongodemoservice.service.DocumentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public long count() {
        return documentRepository.count();
    }

    @Override
    public List<CustomDocument> getAll() {
        return documentRepository.findAll();
    }

    @Override
    public CustomDocument getById(String id) {
        return documentRepository.findById(id)
                .orElseThrow(DocumentNotFoundException::new);
    }

    @Override
    public List<CustomDocument> getByAttribute(String attribute) {
        return documentRepository.findByAttribute(attribute);
    }

    @Override
    public List<CustomDocument> getByAttributeExist() {
        return documentRepository.findByAttributeExist();
    }

    @Override
    public List<CustomDocument> getByAttributeContains(String attribute) {
        return documentRepository.findByAttributeContains(attribute);
    }

    @Override
    public CustomDocument insert(CustomDocument document) {
        return documentRepository.insert(document);
    }

    @Override
    public CustomDocument update(CustomDocument document) {
        return documentRepository.save(document);
    }

    @Override
    public void deleteById(String id) {
        documentRepository.deleteById(id);
    }
}
