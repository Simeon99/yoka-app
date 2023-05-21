package com.yoka.yokafurniture.controller;

import com.yoka.yokafurniture.payload.CategoryDto;
import com.yoka.yokafurniture.payload.CategoryResponse;
import com.yoka.yokafurniture.service.CategoryService;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){

        CategoryDto categoryResponse = categoryService.createCategory(categoryDto);

        return new ResponseEntity<>(categoryResponse, HttpStatus.CREATED);

    }

    @GetMapping
    public List<CategoryResponse> getAllCategories(@RequestHeader(name = "Accept-Language", required = false) Locale locale){
        return categoryService.getAllCategories(LocaleContextHolder.getLocale());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,
                                                      @PathVariable long id){
        return new ResponseEntity<>(categoryService.updateCategory(categoryDto,id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Category successfully deleted.");
    }


}
