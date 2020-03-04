package com.rasse.mongodemoservice.domen;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "documents")
public class CustomDocument {

    @Id
    private String id;

    private BaseDocument document;

    @DBRef(lazy = true)
    private List<Attribute> attributes;

    @DBRef
    private Owner owner;

    public CustomDocument() {
        this.attributes = new ArrayList<>();
    }

    public CustomDocument(BaseDocument document, List<Attribute> attributes, Owner owner) {
        this.document = document;
        this.attributes = attributes;
        this.owner = owner;
    }
}
