package com.rasse.mongodemoservice.service;

import com.rasse.mongodemoservice.domen.CustomDocument;
import com.rasse.mongodemoservice.domen.Owner;

import java.util.List;

public interface OwnerService {
    long count();
    List<Owner> getAll();
    Owner getById(String id);
    Owner insert(Owner owner);
    Owner update(Owner owner);
    void deleteById(String id);
}
