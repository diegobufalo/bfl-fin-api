package com.bfl_fin.api.dtos.response;

import com.bfl_fin.api.enums.AccountType;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountResponse {
    private Long id;
    private String name;
    private BigDecimal balance;
    private AccountType type;
}
