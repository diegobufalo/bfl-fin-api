package com.bfl_fin.api.services;

import com.bfl_fin.api.enums.TransactionType;
import com.bfl_fin.api.model.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    Category create(Category category);
    List<Category> getByUserAndType(UUID userId, TransactionType type);
}
