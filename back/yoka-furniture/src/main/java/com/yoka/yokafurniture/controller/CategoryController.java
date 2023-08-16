package com.yoka.yokafurniture.controller;

import com.yoka.yokafurniture.payload.Category.CategoryDto;
import com.yoka.yokafurniture.payload.Category.CategoryResponse;
import com.yoka.yokafurniture.service.CategoryService;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
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

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories(@RequestHeader(name = "Accept-Language", required = false) Locale locale){
        return ResponseEntity.ok(categoryService.getAllCategories(LocaleContextHolder.getLocale()));
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public  ResponseEntity<CategoryResponse> getCategoryById(@PathVariable long id, @RequestHeader(name = "Accept-Language", required = false) String localeString){
        Locale locale = new Locale(localeString);
        return ResponseEntity.ok(categoryService.getCategoryById(id, LocaleContextHolder.getLocale()));
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
