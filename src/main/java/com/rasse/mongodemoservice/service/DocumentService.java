package com.rasse.mongodemoservice.service;

import com.rasse.mongodemoservice.domen.CustomDocument;

import java.util.List;

public interface DocumentService {
    long count();
    List<CustomDocument> getAll();
    CustomDocument getById(String id);
    List<CustomDocument> getByAttribute(String attribute);
    List<CustomDocument> getByAttributeContains(String attribute);
    List<CustomDocument> getByAttributeExist();
    CustomDocument insert(CustomDocument document);
    CustomDocument update(CustomDocument document);
    void deleteById(String id);
}
