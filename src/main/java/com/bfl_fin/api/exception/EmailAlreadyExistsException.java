package com.bfl_fin.api.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    private static final String DUPLICATED_EMAIL = "Email já está em uso";
    public EmailAlreadyExistsException() {
        super(DUPLICATED_EMAIL);
    }
}
