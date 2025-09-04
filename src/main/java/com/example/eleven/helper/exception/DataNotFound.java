package com.example.eleven.helper.exception;

import javax.persistence.EntityNotFoundException;

public class DataNotFound extends EntityNotFoundException {
    public DataNotFound(String message) {
        super(message);
    }
}

