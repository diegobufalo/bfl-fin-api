package com.bfl_fin.api.repositories;

import com.bfl_fin.api.enums.AccountType;
import com.bfl_fin.api.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByUserId(UUID userId);

    Optional<Account> findByUserIdAndId(UUID userId, Long id);

    boolean existsByUserIdAndNameAndType(UUID userId, String name, AccountType type);
}
