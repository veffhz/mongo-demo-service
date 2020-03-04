package com.rasse.mongodemoservice.dao.impl;

import com.rasse.mongodemoservice.dao.AttributeCriteriaRepository;
import com.rasse.mongodemoservice.domen.Attribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class AttributeRepositoryImpl implements AttributeCriteriaRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public AttributeRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Attribute> findBy(String key, String value) {
        Query query = new Query();
        query.addCriteria(Criteria.where(String.format("params.%s", key)).is(value));
        return mongoTemplate.find(query, Attribute.class);
    }

}
