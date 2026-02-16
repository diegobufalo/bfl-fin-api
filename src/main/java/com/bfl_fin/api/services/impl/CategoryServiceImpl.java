package com.bfl_fin.api.services.impl;

import com.bfl_fin.api.dtos.request.CreateCategoryRequest;
import com.bfl_fin.api.dtos.request.UpdateCategoryRequest;
import com.bfl_fin.api.exception.DuplicatedEntityException;
import com.bfl_fin.api.exception.NotFoundException;
import com.bfl_fin.api.mapper.CategoryMapper;
import com.bfl_fin.api.model.Category;
import com.bfl_fin.api.model.User;
import com.bfl_fin.api.repositories.CategoryRepository;
import com.bfl_fin.api.services.CategoryService;
import com.bfl_fin.api.services.UserService;
import com.bfl_fin.api.utils.ConstMessages;
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
    private final CategoryMapper categoryMapper;
    private final UserService userService;

    @Override
    public Category create(UUID userId, CreateCategoryRequest req) {
        User user = userService.findById(userId);
        if (categoryRepo.existsByUserIdAndNameAndType(userId, req.getName(), req.getType()))
            throw new DuplicatedEntityException(ConstMessages.CATEGORY_DUPLICATED);

        Category category = categoryMapper.fromRequest(req);
        category.setUser(user);
        return categoryRepo.save(category);
    }

    @Override
    public Category update(UUID userId, Long categoryId, UpdateCategoryRequest req) {
        Category category = this.getByUserAndId(userId, categoryId);
        if (categoryRepo.existsByUserIdAndNameAndType(userId, req.getName(), req.getType()))
            throw new DuplicatedEntityException(ConstMessages.CATEGORY_DUPLICATED);

        category.setName(req.getName());
        category.setType(req.getType());
        return category;
    }

    @Override
    public void changeActivationStatus(UUID userId, Long categoryId, boolean newActivationState) {
        Category category = this.getByUserAndId(userId, categoryId);
        category.setActive(newActivationState);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> getByUser(UUID userId) {
        return categoryRepo.findByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public Category getByUserAndId(UUID userId, Long categoryId) {
        return categoryRepo.findByUserIdAndId(userId, categoryId)
                .orElseThrow(() -> new NotFoundException(ConstMessages.CATEGORY_NOT_FOUND));
    }
}
