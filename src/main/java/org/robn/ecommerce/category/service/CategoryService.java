package org.robn.ecommerce.category.service;

import org.robn.ecommerce.category.model.Category;
import org.robn.ecommerce.category.model.request.CategoryCreateRequest;
import org.robn.ecommerce.category.model.request.CategoryUpdateRequest;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    Category findById(Long id);

    void create(CategoryCreateRequest categoryCreateRequest);

    void update(Long id, CategoryUpdateRequest categoryUpdateRequest);

}
