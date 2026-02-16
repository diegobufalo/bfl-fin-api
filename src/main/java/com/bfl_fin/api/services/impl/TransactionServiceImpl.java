package com.bfl_fin.api.services.impl;

import com.bfl_fin.api.enums.TransactionType;
import com.bfl_fin.api.model.Transaction;
import com.bfl_fin.api.repositories.TransactionRepository;
import com.bfl_fin.api.services.AccountService;
import com.bfl_fin.api.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepo;
    private final AccountService accountService;

    public Transaction create(Transaction transaction) {
        BigDecimal delta = transaction.getType() == TransactionType.INCOME ? transaction.getAmount() :
                transaction.getType() == TransactionType.EXPENSE ? transaction.getAmount().negate() : BigDecimal.ZERO;
        Transaction saved = transactionRepo.save(transaction);
        accountService.updateBalance(transaction.getAccount().getId(), delta);
        return saved;
    }

    @Transactional(readOnly = true)
    public List<Transaction> getByUser(UUID userId, LocalDate start, LocalDate end) {
        return transactionRepo.findByAccountUserIdAndDateBetween(userId, start, end);
    }
}
