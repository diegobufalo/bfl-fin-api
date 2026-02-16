package com.bfl_fin.api.controller;

import com.bfl_fin.api.dtos.request.CreateAccountRequest;
import com.bfl_fin.api.dtos.request.UpdateAccountRequest;
import com.bfl_fin.api.dtos.response.AccountResponse;
import com.bfl_fin.api.mapper.AccountMapper;
import com.bfl_fin.api.model.Account;
import com.bfl_fin.api.model.User;
import com.bfl_fin.api.services.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final AccountMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponse create(@AuthenticationPrincipal User user,
                                  @Valid @RequestBody CreateAccountRequest request) {
        Account account = accountService.create(user.getId(), request);
        return mapper.toResponse(account);
    }

    @GetMapping
    public List<AccountResponse> getMyAccounts(@AuthenticationPrincipal User user) {
        return accountService.getByUser(user.getId()).stream()
                .map(mapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public AccountResponse getAccount(@AuthenticationPrincipal User user, @PathVariable Long id) {
        Account acc = accountService.getByUserIdAndId(user.getId(), id);
        return mapper.toResponse(acc);
    }

    @PutMapping("/{id}")
    public AccountResponse update(@AuthenticationPrincipal User user,
                                  @PathVariable Long id,
                                  @Valid @RequestBody UpdateAccountRequest request) {
        Account account = accountService.update(user.getId(), id, request);
        return mapper.toResponse(account);
    }

    @PutMapping("/{id}/activation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal User user, @PathVariable Long id, @RequestParam boolean activationStatus) {
        accountService.changeActivationStatus(user.getId(), id, activationStatus);
    }
}
