package com.tvd12.ezydata.example.jpa.service;

import com.tvd12.ezydata.example.jpa.converter.DataToEntityConverter;
import com.tvd12.ezydata.example.jpa.converter.EntityToDataConverter;
import com.tvd12.ezydata.example.jpa.data.AddCategoryData;
import com.tvd12.ezydata.example.jpa.data.CategoryData;
import com.tvd12.ezydata.example.jpa.entity.Category;
import com.tvd12.ezydata.example.jpa.exception.CategoryNotFoundException;
import com.tvd12.ezydata.example.jpa.exception.DuplicatedCategoryException;
import com.tvd12.ezydata.example.jpa.repository.CategoryRepository;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import lombok.AllArgsConstructor;

@EzySingleton
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final DataToEntityConverter dataToEntityConverter;
    private final EntityToDataConverter entityToDataConverter;

    public CategoryData saveCategory(AddCategoryData data) {
        final Category existedCategory = categoryRepository.findByName(data.getCategoryName());
        if (existedCategory != null) {
            throw new DuplicatedCategoryException("category named: " + data.getCategoryName() + " existed");
        }
        final Category entity = dataToEntityConverter.toEntity(data);
        categoryRepository.save(entity);
        return entityToDataConverter.toData(entity);
    }

    public CategoryData getCategory(long categoryId) {
        final Category entity = categoryRepository.findById(categoryId);
        if (entity == null) {
            throw new CategoryNotFoundException("not found category with id: " + categoryId);
        }
        return entityToDataConverter.toData(entity);
    }
}
