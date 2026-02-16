package com.bfl_fin.api.exception;

public class DuplicatedEntityException extends RuntimeException {
    public DuplicatedEntityException(String message) {
        super(message);
    }
}