package org.robn.ecommerce.category.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.category.model.Category;
import org.robn.ecommerce.category.model.mapper.CategoryDomainToListResponseMapper;
import org.robn.ecommerce.category.model.mapper.CategoryDomainToResponseMapper;
import org.robn.ecommerce.category.model.request.CategoryCreateRequest;
import org.robn.ecommerce.category.model.request.CategoryUpdateRequest;
import org.robn.ecommerce.category.model.response.CategoryListResponse;
import org.robn.ecommerce.category.model.response.CategoryResponse;
import org.robn.ecommerce.category.service.CategoryService;
import org.robn.ecommerce.common.model.response.EcoBaseResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryDomainToResponseMapper categoryDomainToResponseMapper;
    private final CategoryDomainToListResponseMapper categoryDomainToListResponseMapper;

    @GetMapping
    @PreAuthorize("permitAll()")
    public EcoBaseResponse<List<CategoryListResponse>> findAll() {
        final List<Category> categories = categoryService.findAll();
        final List<CategoryListResponse> categoriesResponse = categoryDomainToListResponseMapper.map(categories);

        return EcoBaseResponse.successOf(categoriesResponse);
    }

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public EcoBaseResponse<CategoryResponse> findById(@PathVariable final Long id) {
        final Category category = categoryService.findById(id);
        final CategoryResponse categoryResponse = categoryDomainToResponseMapper.map(category);

        return EcoBaseResponse.successOf(categoryResponse);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public EcoBaseResponse<Void> create(@RequestBody @Valid final CategoryCreateRequest categoryCreateRequest) {
        categoryService.create(categoryCreateRequest);

        return EcoBaseResponse.success();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public EcoBaseResponse<Void> update(@PathVariable final Long id, @RequestBody @Valid final CategoryUpdateRequest categoryUpdateRequest) {
        categoryService.update(id, categoryUpdateRequest);

        return EcoBaseResponse.success();
    }

}
