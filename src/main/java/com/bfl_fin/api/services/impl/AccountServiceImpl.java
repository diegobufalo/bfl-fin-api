package com.bfl_fin.api.services.impl;

import com.bfl_fin.api.dtos.request.CreateAccountRequest;
import com.bfl_fin.api.dtos.request.UpdateAccountRequest;
import com.bfl_fin.api.exception.NotFoundException;
import com.bfl_fin.api.mapper.AccountMapper;
import com.bfl_fin.api.model.Account;
import com.bfl_fin.api.model.User;
import com.bfl_fin.api.repositories.AccountRepository;
import com.bfl_fin.api.repositories.UserRepository;
import com.bfl_fin.api.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final AccountMapper accountMapper;

    @Override
    public Account create(UUID userId, CreateAccountRequest req) {
        User user = userRepository.findById(userId).orElseThrow();
        Account acc = accountMapper.fromRequest(req);
        acc.setUser(user);
        return accountRepository.save(acc);
    }

    @Override
    public Account update(UUID userId, Long accountId, UpdateAccountRequest request) {
        Account acc = accountRepository.findByUserIdAndId(userId, accountId)
                .orElseThrow(() -> new NotFoundException("Conta não encontrada"));
        acc.setName(request.getName());
        acc.setType(request.getType());
        return acc;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Account> getByUser(UUID userId) {
        return accountRepository.findByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public Account getByUserIdAndId(UUID userId, Long accountId) {
        return accountRepository.findByUserIdAndId(userId, accountId)
                .orElseThrow(() -> new NotFoundException("Conta não encontrada"));
    }

    @Override
    public void updateBalance(Long accountId, BigDecimal delta) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new NotFoundException("Conta não encontrada"));
        account.setBalance(account.getBalance().add(delta));
    }

    @Override
    public void deleteAccount(UUID userId, Long accountId) {
        Account acc = accountRepository.findByUserIdAndId(userId, accountId)
                .orElseThrow(() -> new NotFoundException("Conta não encontrada"));
        accountRepository.delete(acc);
    }
}
