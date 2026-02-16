package com.bfl_fin.api.services;

import com.bfl_fin.api.model.Transaction;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TransactionService {

    Transaction create(Transaction transaction);

    List<Transaction> getByUser(UUID userId, LocalDate start, LocalDate end);

    Transaction getByAccountIdAndId(Long accountId, Long transactionId);
}
