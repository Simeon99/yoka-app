package com.yoka.yokafurniture.service;

import com.yoka.yokafurniture.payload.Category.CategoryDto;
import com.yoka.yokafurniture.payload.Category.CategoryResponse;

import java.util.List;
import java.util.Locale;

public interface CategoryService {
    public CategoryDto createCategory(CategoryDto categoryDto);
    public List<CategoryResponse> getAllCategories(Locale locale);
    public CategoryResponse getCategoryById(long id,Locale locale);
    public CategoryDto updateCategory(CategoryDto categoryDto, long id);
    public void deleteCategory(long id);
}
