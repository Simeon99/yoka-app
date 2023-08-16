package com.yoka.yokafurniture.service.impl;

import com.yoka.yokafurniture.entity.Category;
import com.yoka.yokafurniture.exception.ResourceNotFoundException;
import com.yoka.yokafurniture.payload.Category.CategoryDto;
import com.yoka.yokafurniture.payload.Category.CategoryResponse;
import com.yoka.yokafurniture.repository.CategoryRepository;
import com.yoka.yokafurniture.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        Category category = mapToCategory(categoryDto);

        Category categoryResponse = categoryRepository.save(category);


        return mapToDto(category);
    }

    @Override
    public List<CategoryResponse> getAllCategories(Locale locale) {

        List<Category> categories = categoryRepository.findAll();

        if (locale.toString().equalsIgnoreCase("sr")){
            return categories.stream().map(category -> mapToDtoSr(category)).toList();
        }else if(locale.toString().equalsIgnoreCase("en")){
            return  categories.stream().map(category -> mapToDtoEn(category)).toList();
        }

        return  categories.stream().map(category -> mapToDtoEn(category)).toList();
    }

    @Override
    public CategoryResponse getCategoryById(long id, Locale locale) {

        Category category = categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category","id", id));

        if (locale.toString().equalsIgnoreCase("sr")){
            return mapToDtoSr(category);
        }else if(locale.toString().equalsIgnoreCase("en")){
            return  mapToDtoEn(category);
        }

        return  mapToDtoEn(category);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, long id) {

        Category category = categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category","id", id));

        category.setName(categoryDto.getName());
        category.setNameSr(categoryDto.getNameSr());
        category.setShippingPrice(categoryDto.getShippingPrice());

        Category updatedCategory = categoryRepository.save(category);

        return mapToDto(updatedCategory);
    }

    @Override
    public void deleteCategory(long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category","id", id));
        categoryRepository.delete(category);
    }

    private CategoryDto mapToDto(Category category){
        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
        return categoryDto;
    }

    private CategoryResponse mapToDtoSr(Category category){
        CategoryResponse categoryResponce = new CategoryResponse();
        categoryResponce.setId(category.getId());
        categoryResponce.setName(category.getNameSr());
        categoryResponce.setShippingPrice(category.getShippingPrice());
        return categoryResponce;
    }

    private CategoryResponse mapToDtoEn(Category category){
        CategoryResponse categoryResponce = modelMapper.map(category, CategoryResponse.class);
        return categoryResponce;
    }

    private Category mapToCategory(CategoryDto categoryDto){
        Category category = modelMapper.map(categoryDto, Category.class);
        return category;
    }

}
