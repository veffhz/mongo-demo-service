package com.rasse.mongodemoservice.service.impl;

import com.rasse.mongodemoservice.dao.OwnerRepository;
import com.rasse.mongodemoservice.domen.Owner;
import com.rasse.mongodemoservice.exceptions.OwnerNotFoundException;
import com.rasse.mongodemoservice.service.OwnerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    @Autowired
    public OwnerServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public long count() {
        return ownerRepository.count();
    }

    @Override
    public List<Owner> getAll() {
        return ownerRepository.findAll();
    }

    @Override
    public Owner getById(String id) {
        return ownerRepository.findById(id)
                .orElseThrow(OwnerNotFoundException::new);
    }

    @Override
    public Owner insert(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public Owner update(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public void deleteById(String id) {
        ownerRepository.deleteById(id);
    }
}
