package com.bfl_fin.api.repositories;

import com.bfl_fin.api.model.Recurrence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecurrenceRepository extends JpaRepository<Recurrence, Long> {
}
