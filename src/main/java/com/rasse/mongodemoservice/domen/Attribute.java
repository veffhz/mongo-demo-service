package com.rasse.mongodemoservice.domen;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Data
@Document(collection = "attributes")
public class Attribute {

    @Id
    private String id;

    @Indexed
    private String name;

    private Map<String, Object> params;

    public void putParam(String name, Object value) {
        this.params.put(name, value);
    }

    public Attribute() {
        this.params = new HashMap<>();
    }
}
