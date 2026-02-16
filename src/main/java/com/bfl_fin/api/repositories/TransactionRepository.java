package com.bfl_fin.api.repositories;

import com.bfl_fin.api.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<Transaction> findByAccountIdAndId(Long accountId, Long id);

    @Query("select t from Transaction t where t.account.user.id = ?1 and t.date between ?2 and ?3")
    List<Transaction> findByAccountUserIdAndDateBetween(UUID id, LocalDate createdAtStart, LocalDate createdAtEnd);
}
