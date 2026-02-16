package com.bfl_fin.api.services.impl;

import com.bfl_fin.api.dtos.request.CreateAccountRequest;
import com.bfl_fin.api.dtos.request.UpdateAccountRequest;
import com.bfl_fin.api.exception.DuplicatedEntityException;
import com.bfl_fin.api.exception.NotFoundException;
import com.bfl_fin.api.mapper.AccountMapper;
import com.bfl_fin.api.model.Account;
import com.bfl_fin.api.model.User;
import com.bfl_fin.api.repositories.AccountRepository;
import com.bfl_fin.api.services.AccountService;
import com.bfl_fin.api.services.UserService;
import com.bfl_fin.api.utils.ConstMessages;
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
    private final AccountMapper accountMapper;
    private final UserService userService;

    @Override
    public Account create(UUID userId, CreateAccountRequest req) {
        User user = userService.findById(userId);
        if (accountRepository.existsByUserIdAndNameAndType(userId, req.getName(), req.getType()))
            throw new DuplicatedEntityException(ConstMessages.ACCOUNT_DUPLICATED);

        Account acc = accountMapper.fromRequest(req);
        acc.setUser(user);
        return accountRepository.save(acc);
    }

    @Override
    public Account update(UUID userId, Long accountId, UpdateAccountRequest req) {
        Account acc = this.getByUserIdAndId(userId, accountId);
        if (accountRepository.existsByUserIdAndNameAndType(userId, req.getName(), req.getType()))
            throw new DuplicatedEntityException(ConstMessages.ACCOUNT_DUPLICATED);

        acc.setName(req.getName());
        acc.setType(req.getType());
        return acc;
    }

    @Override
    public void changeActivationStatus(UUID userId, Long accountId, boolean newActivationStatus) {
        Account acc = this.getByUserIdAndId(userId, accountId);
        acc.setActive(newActivationStatus);
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
                .orElseThrow(() -> new NotFoundException(ConstMessages.ACCOUNT_NOT_FOUND));
    }

    @Override
    public void updateBalance(Long accountId, BigDecimal delta) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new NotFoundException(ConstMessages.ACCOUNT_NOT_FOUND));
        account.setBalance(account.getBalance().add(delta));
    }
}
