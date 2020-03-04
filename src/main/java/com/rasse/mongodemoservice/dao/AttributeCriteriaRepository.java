package com.rasse.mongodemoservice.dao;

import com.rasse.mongodemoservice.domen.Attribute;

import java.util.List;

public interface AttributeCriteriaRepository {
    List<Attribute> findBy(String key, String value);
}
