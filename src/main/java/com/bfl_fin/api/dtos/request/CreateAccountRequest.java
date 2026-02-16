package com.bfl_fin.api.dtos.request;

import com.bfl_fin.api.enums.AccountType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateAccountRequest {
    @NotBlank
    private String name;
    private AccountType type;
    private BigDecimal balance;
}
