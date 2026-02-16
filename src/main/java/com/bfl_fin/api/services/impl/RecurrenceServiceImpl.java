package com.bfl_fin.api.services.impl;

import com.bfl_fin.api.enums.RecurrenceFrequency;
import com.bfl_fin.api.enums.TransactionType;
import com.bfl_fin.api.model.Recurrence;
import com.bfl_fin.api.model.Transaction;
import com.bfl_fin.api.repositories.RecurrenceRepository;
import com.bfl_fin.api.services.RecurrenceService;
import com.bfl_fin.api.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
public class RecurrenceServiceImpl implements RecurrenceService {

    private final RecurrenceRepository recurrenceRepo;
    private final TransactionService transactionService;

    public Recurrence create(Recurrence recurrence) {
        if (recurrence.getNextExecutionDate() == null) {
            recurrence.setNextExecutionDate(recurrence.getStartDate());
        }
        return recurrenceRepo.save(recurrence);
    }

    @Scheduled(cron = "0 0 2 * * ?")
    @Transactional
    public void processActiveRecurrences() {
        LocalDate today = LocalDate.now();
        recurrenceRepo.findAll().stream()
                .filter(Recurrence::isActive)
                .filter(r -> r.getNextExecutionDate() != null && !r.getNextExecutionDate().isAfter(today))
                .forEach(this::generateTransaction);
    }

    private void generateTransaction(Recurrence r) {
        Transaction tx = new Transaction();
        tx.setAccount(r.getAccount());
        tx.setCategory(r.getCategory());
        tx.setAmount(r.getAmount());
        tx.setType(r.getAmount().compareTo(BigDecimal.ZERO) > 0 ? TransactionType.INCOME : TransactionType.EXPENSE);
        tx.setDescription("Recurring: " + r.getDescription());
        transactionService.create(tx);

        // Advance nextExecution
        r.setNextExecutionDate(advanceDate(r.getNextExecutionDate(), r.getFrequency()));
        if (r.getEndDate() != null && r.getNextExecutionDate().isAfter(r.getEndDate())) {
            r.setActive(false);
        }
        recurrenceRepo.save(r);
    }

    private LocalDate advanceDate(LocalDate date, RecurrenceFrequency freq) {
        return switch (freq) {
            case MONTHLY -> date.plusMonths(1);
            case WEEKLY -> date.plusWeeks(1);
            case YEARLY -> date.plusYears(1);
            case DAILY -> date.plusDays(1);
        };
    }
}
