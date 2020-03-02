package com.rasse.mongodemoservice.api;

import com.rasse.mongodemoservice.exceptions.OwnerNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ErrorHandler {

    @ExceptionHandler(value = {
            ChangeSetPersister.NotFoundException.class, OwnerNotFoundException.class
    })
    public ResponseEntity<String> handle(RuntimeException ex) {
        return new ResponseEntity<>("Error: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}
