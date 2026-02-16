package com.bfl_fin.api.controller;

import com.bfl_fin.api.exception.DuplicatedEntityException;
import com.bfl_fin.api.exception.EmailAlreadyExistsException;
import com.bfl_fin.api.exception.NotFoundException;
import com.bfl_fin.api.model.RestErrorModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
@RequiredArgsConstructor
public class ControllerErrorHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public RestErrorModel handleBindException(EmailAlreadyExistsException e) {
        log.error(e);
        return new RestErrorModel(e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public RestErrorModel handleBindException(NotFoundException e) {
        log.error(e);
        return new RestErrorModel(e.getMessage());
    }

    @ExceptionHandler(DuplicatedEntityException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public RestErrorModel handleBindException(DuplicatedEntityException e) {
        log.error(e);
        return new RestErrorModel(e.getMessage());
    }
}
