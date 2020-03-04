package com.rasse.mongodemoservice.service.impl;

import com.rasse.mongodemoservice.dao.AttributeRepository;
import com.rasse.mongodemoservice.domen.Attribute;
import com.rasse.mongodemoservice.service.AttributeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttributeServiceImpl implements AttributeService {

    private final AttributeRepository attributeRepository;

    @Autowired
    public AttributeServiceImpl(AttributeRepository attributeRepository) {
        this.attributeRepository = attributeRepository;
    }

    @Override
    public long count() {
        return attributeRepository.count();
    }

    @Override
    public List<Attribute> getAll() {
        return attributeRepository.findAll();
    }

    @Override
    public Attribute getById(String id) {
        return attributeRepository.findById(id).orElse(null);
    }

    @Override
    public Attribute insert(Attribute attribute) {
        return attributeRepository.save(attribute);
    }

    @Override
    public Attribute update(Attribute attribute) {
        return attributeRepository.save(attribute);
    }

    @Override
    public void deleteById(String id) {
        attributeRepository.deleteById(id);
    }

    @Override
    public List<Attribute> findBy(String key, String value) {
        return attributeRepository.findBy(key, value);
    }
}
