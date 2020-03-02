package com.rasse.mongodemoservice.domen;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "documents")
public class CustomDocument {

    @Id
    private String id;

    private BaseDocument document;

    private Attribute attribute;

    @DBRef
    private Owner owner;

    public CustomDocument(BaseDocument document, Attribute attribute, Owner owner) {
        this.document = document;
        this.attribute = attribute;
        this.owner = owner;
    }
}
