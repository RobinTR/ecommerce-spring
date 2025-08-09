package org.robn.ecommerce.category.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.category.model.Category;
import org.robn.ecommerce.category.model.mapper.CategoryToListResponseMapper;
import org.robn.ecommerce.category.model.mapper.CategoryToResponseMapper;
import org.robn.ecommerce.category.model.request.CategoryCreateRequest;
import org.robn.ecommerce.category.model.request.CategoryUpdateRequest;
import org.robn.ecommerce.category.model.response.CategoryListResponse;
import org.robn.ecommerce.category.model.response.CategoryResponse;
import org.robn.ecommerce.category.service.CategoryService;
import org.robn.ecommerce.common.model.response.EcoBaseResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryToResponseMapper categoryToResponseMapper;
    private final CategoryToListResponseMapper categoryToListResponseMapper;

    @GetMapping
    public EcoBaseResponse<List<CategoryListResponse>> findAll() {
        List<Category> categories = categoryService.findAll();
        List<CategoryListResponse> categoriesResponse = categoryToListResponseMapper.map(categories);

        return EcoBaseResponse.successOf(categoriesResponse);
    }

    @GetMapping("/{id}")
    public EcoBaseResponse<CategoryResponse> findById(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        CategoryResponse categoryResponse = categoryToResponseMapper.map(category);

        return EcoBaseResponse.successOf(categoryResponse);
    }

    @PostMapping
    public EcoBaseResponse<Void> create(@RequestBody @Valid CategoryCreateRequest categoryCreateRequest) {
        categoryService.create(categoryCreateRequest);

        return EcoBaseResponse.success();
    }

    @PutMapping("/{id}")
    public EcoBaseResponse<Void> update(@PathVariable Long id, @RequestBody @Valid CategoryUpdateRequest categoryUpdateRequest) {
        categoryService.update(id, categoryUpdateRequest);

        return EcoBaseResponse.success();
    }

}
