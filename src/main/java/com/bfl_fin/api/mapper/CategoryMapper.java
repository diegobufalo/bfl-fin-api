package com.bfl_fin.api.mapper;

import com.bfl_fin.api.dtos.request.CreateCategoryRequest;
import com.bfl_fin.api.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category fromRequest(CreateCategoryRequest req) {
        return Category.builder()
                .name(req.getName())
                .type(req.getType())
                .build();
    }
}
