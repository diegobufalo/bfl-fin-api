package com.bfl_fin.api.services;

import com.bfl_fin.api.dtos.request.CreateAccountRequest;
import com.bfl_fin.api.dtos.request.UpdateAccountRequest;
import com.bfl_fin.api.model.Account;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface AccountService {

    Account create(UUID userId, CreateAccountRequest account);

    Account update(UUID userId, Long accountId, UpdateAccountRequest request);

    void changeActivationStatus(UUID userId, Long accountId, boolean newActivationStatus);

    List<Account> getByUser(UUID userId);

    Account getByUserIdAndId(UUID userId, Long accountId);

    void updateBalance(Long accountId, BigDecimal delta);
}
