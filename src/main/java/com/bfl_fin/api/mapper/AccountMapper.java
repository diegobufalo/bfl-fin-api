package com.bfl_fin.api.mapper;

import com.bfl_fin.api.dtos.request.CreateAccountRequest;
import com.bfl_fin.api.dtos.response.AccountResponse;
import com.bfl_fin.api.model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public Account fromRequest(CreateAccountRequest req) {
        return Account.builder()
                .name(req.getName())
                .type(req.getType())
                .balance(req.getBalance())
                .build();
    }

    public AccountResponse toResponse(Account acc) {
        return AccountResponse.builder()
                .name(acc.getName())
                .id(acc.getId())
                .balance(acc.getBalance())
                .build();
    }
}
