package org.robn.ecommerce.category.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.category.exception.CategoryNotExistException;
import org.robn.ecommerce.category.model.Category;
import org.robn.ecommerce.category.model.mapper.CategoryCreateRequestToDomainMapper;
import org.robn.ecommerce.category.model.request.CategoryCreateRequest;
import org.robn.ecommerce.category.model.request.CategoryUpdateRequest;
import org.robn.ecommerce.category.port.CategoryReadPort;
import org.robn.ecommerce.category.port.CategorySavePort;
import org.robn.ecommerce.category.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

    private final CategoryCreateRequestToDomainMapper categoryCreateRequestToDomainMapper;
    private final CategoryReadPort categoryReadPort;
    private final CategorySavePort categorySavePort;

    @Override
    public List<Category> findAll() {
        return categoryReadPort.findAll();
    }

    @Override
    public Category findById(final Long id) {
        return getExistingCategory(id);
    }

    @Override
    @Transactional
    public void create(final CategoryCreateRequest categoryCreateRequest) {
        final Category category = categoryCreateRequestToDomainMapper.map(categoryCreateRequest);
        categorySavePort.save(category);
    }

    @Override
    @Transactional
    public void update(final Long id, final CategoryUpdateRequest categoryUpdateRequest) {
        final Category categoryToUpdate = getExistingCategory(id);
        categoryToUpdate.setName(categoryUpdateRequest.getName());
        categorySavePort.save(categoryToUpdate);
    }

    private Category getExistingCategory(final Long id) {
        return categoryReadPort.findById(id).orElseThrow(() -> CategoryNotExistException.of(id));
    }

}
