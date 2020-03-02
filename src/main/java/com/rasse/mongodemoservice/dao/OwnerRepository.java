package com.rasse.mongodemoservice.dao;

import com.rasse.mongodemoservice.domen.Owner;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OwnerRepository extends MongoRepository<Owner, String> {
}
