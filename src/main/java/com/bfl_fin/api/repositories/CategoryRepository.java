package com.bfl_fin.api.repositories;

import com.bfl_fin.api.enums.TransactionType;
import com.bfl_fin.api.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByUserIdAndType(UUID userId, TransactionType type);
}
