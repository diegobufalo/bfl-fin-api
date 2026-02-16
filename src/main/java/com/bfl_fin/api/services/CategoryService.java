package com.bfl_fin.api.services;

import com.bfl_fin.api.dtos.request.CreateCategoryRequest;
import com.bfl_fin.api.dtos.request.UpdateCategoryRequest;
import com.bfl_fin.api.model.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    Category create(UUID userId, CreateCategoryRequest req);

    Category update(UUID userId, Long categoryId, UpdateCategoryRequest req);

    void changeActivationStatus(UUID userId, Long categoryId, boolean newActivationState);

    List<Category> getByUser(UUID userId);

    Category getByUserAndId(UUID userId, Long categoryId);
}
