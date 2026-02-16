package com.bfl_fin.api.dtos.request;

import com.bfl_fin.api.enums.TransactionType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateCategoryRequest {
    @NotBlank
    private String name;
    private TransactionType type;
}
