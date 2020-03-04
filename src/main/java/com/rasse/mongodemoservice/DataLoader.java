package com.rasse.mongodemoservice;

import com.rasse.mongodemoservice.dao.AttributeRepository;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class DataLoader implements ApplicationRunner {

    private final DocumentRepository documentRepository;
    private final OwnerRepository ownerRepository;
    private final AttributeRepository attributeRepository;

    private Random random = new Random();

    @Autowired
    public DataLoader(DocumentRepository documentRepository, OwnerRepository ownerRepository,
                      AttributeRepository attributeRepository) {
        this.documentRepository = documentRepository;
        this.ownerRepository = ownerRepository;
        this.attributeRepository = attributeRepository;
    }

    public void run(ApplicationArguments args) {

        Owner owner = new Owner("Test 1", "Test 1");
        Owner owner2 = new Owner("Test 2", "Test 2");

        ownerRepository.saveAll(Arrays.asList(owner, owner2));

        CustomDocument customDocument1 = getNewDocument("title-1", owner);
        CustomDocument customDocument2 = getNewDocument("title-2", owner2);
        CustomDocument customDocument3 = getNewDocument("title-3", owner);
        CustomDocument customDocument4 = getNewDocument("title-4", owner2);
        CustomDocument customDocument5 = getNewDocument("title-5", owner);
        CustomDocument customDocument6 = getNewDocument("title-6", owner2);

        documentRepository.saveAll(Arrays.asList(
                customDocument1, customDocument2, customDocument3,
                customDocument4, customDocument5, customDocument6
        ));
    }

    private CustomDocument getNewDocument(String title, Owner owner) {
        List<Attribute> attributes = new ArrayList<>();

        for (int j = 0; j < 10; j++) {
            Attribute attr = new Attribute();
            attr.setName(String.format("attribute %d", j));
            for (int i = 0; i < 10; i++) {
                attr.putParam(String.format("param_%d", i), randomString(20));
            }
            attributes.add(attr);
        }

        attributeRepository.saveAll(attributes);

        return new CustomDocument(new BaseDocument(title, LocalDateTime.now()), attributes, owner);
    }

    public String randomString(int length) {
        return random.ints(97, 122)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
