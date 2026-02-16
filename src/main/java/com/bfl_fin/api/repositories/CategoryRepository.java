package com.bfl_fin.api.repositories;

import com.bfl_fin.api.enums.TransactionType;
import com.bfl_fin.api.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByUserIdAndNameAndType(UUID userId, String name, TransactionType type);
    Optional<Category> findByUserIdAndId(UUID userId, Long id);
    List<Category> findByUserId(UUID userId);
}
