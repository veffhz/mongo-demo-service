package com.rasse.mongodemoservice.service;

import com.rasse.mongodemoservice.domen.Attribute;

import java.util.List;

public interface AttributeService {
    long count();
    List<Attribute> getAll();
    Attribute getById(String id);
    Attribute insert(Attribute attribute);
    Attribute update(Attribute attribute);
    void deleteById(String id);
    List<Attribute> findBy(String key, String value);
}
