package com.rasse.mongodemoservice.dao;

import com.rasse.mongodemoservice.domen.CustomDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface DocumentRepository extends MongoRepository<CustomDocument, String> {
    @Query("{'attribute.name': ?0}")
    List<CustomDocument> findByAttribute(String attribute);

    @Query("{'attribute.name': { $exists: true } }")
    List<CustomDocument> findByAttributeExist();

    @Query("{'attribute.name': { $regex : ?0} }")
    List<CustomDocument> findByAttributeContains(String bookName);
}
