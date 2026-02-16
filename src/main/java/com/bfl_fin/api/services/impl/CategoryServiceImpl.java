package com.bfl_fin.api.services.impl;

import com.bfl_fin.api.enums.TransactionType;
import com.bfl_fin.api.model.Category;
import com.bfl_fin.api.repositories.CategoryRepository;
import com.bfl_fin.api.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepo;

    public Category create(Category category) {
        return categoryRepo.save(category);
    }

    @Transactional(readOnly = true)
    public List<Category> getByUserAndType(UUID userId, TransactionType type) {
        return categoryRepo.findByUserIdAndType(userId, type);
    }
}
