package com.bfl_fin.api.dtos.request;

import com.bfl_fin.api.enums.AccountType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateAccountRequest {
    @NotBlank
    private String name;
    private AccountType type;
}
