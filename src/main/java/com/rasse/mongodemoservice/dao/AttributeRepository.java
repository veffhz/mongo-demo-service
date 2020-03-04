package com.rasse.mongodemoservice.dao;

import com.rasse.mongodemoservice.domen.Attribute;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AttributeRepository extends MongoRepository<Attribute, String>, AttributeCriteriaRepository {
}
