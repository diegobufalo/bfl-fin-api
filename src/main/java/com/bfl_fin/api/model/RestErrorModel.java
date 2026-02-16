package com.bfl_fin.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RestErrorModel {

    private String errorMessage;
    private LocalDateTime errorTime;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errorCode;

    public RestErrorModel(String errorMessage) {
        this.errorMessage = errorMessage;
        this.errorTime = LocalDateTime.now();
    }

    public RestErrorModel(String errorMessage, String errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.errorTime = LocalDateTime.now();
    }
}
