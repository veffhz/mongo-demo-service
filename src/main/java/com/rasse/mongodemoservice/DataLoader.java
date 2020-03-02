package com.rasse.mongodemoservice;

import com.rasse.mongodemoservice.dao.DocumentRepository;
import com.rasse.mongodemoservice.dao.OwnerRepository;
import com.rasse.mongodemoservice.domen.Attribute;
import com.rasse.mongodemoservice.domen.BaseDocument;
import com.rasse.mongodemoservice.domen.CustomDocument;
import com.rasse.mongodemoservice.domen.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class DataLoader implements ApplicationRunner {

    private final DocumentRepository documentRepository;
    private final OwnerRepository ownerRepository;

    @Autowired
    public DataLoader(DocumentRepository documentRepository, OwnerRepository ownerRepository) {
        this.documentRepository = documentRepository;
        this.ownerRepository = ownerRepository;
    }

    public void run(ApplicationArguments args) {

        Owner owner = new Owner("Test 1", "Test 1");
        Owner owner2 = new Owner("Test 2", "Test 2");

        ownerRepository.saveAll(Arrays.asList(owner, owner2));

        CustomDocument customDocument = new CustomDocument(new BaseDocument("title", LocalDateTime.now()), new Attribute("param"), owner);
        CustomDocument customDocument2 = new CustomDocument(new BaseDocument("title 2", LocalDateTime.now()), new Attribute("param"), owner);
        CustomDocument customDocument3 = new CustomDocument(new BaseDocument("title 3", LocalDateTime.now()), new Attribute("param", "param2"), owner);

        CustomDocument customDocument4 = new CustomDocument(new BaseDocument("title 2", LocalDateTime.now()), new Attribute(null), owner);
        CustomDocument customDocument5 = new CustomDocument(new BaseDocument("title 2", LocalDateTime.now()), null, owner);

        documentRepository.saveAll(Arrays.asList(
                customDocument, customDocument2, customDocument3, customDocument4, customDocument5
        ));
    }
}
