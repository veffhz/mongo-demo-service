package com.rasse.mongodemoservice.exceptions;

public class OwnerNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Owner not found!";

    public OwnerNotFoundException() {
        super(MESSAGE);
    }

    public OwnerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public OwnerNotFoundException(Throwable cause) {
        super(MESSAGE, cause);
    }
}
