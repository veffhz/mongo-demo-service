package com.rasse.mongodemoservice.domen;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BaseDocument {

    private String title;
    private LocalDateTime birthDate;

    public BaseDocument(String title, LocalDateTime now) {

    }
}
