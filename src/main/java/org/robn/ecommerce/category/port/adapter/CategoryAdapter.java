package org.robn.ecommerce.category.port.adapter;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.category.model.Category;
import org.robn.ecommerce.category.model.entity.CategoryEntity;
import org.robn.ecommerce.category.model.mapper.CategoryDomainToEntityMapper;
import org.robn.ecommerce.category.model.mapper.CategoryEntityToDomainMapper;
import org.robn.ecommerce.category.port.CategoryReadPort;
import org.robn.ecommerce.category.port.CategorySavePort;
import org.robn.ecommerce.category.repository.CategoryRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CategoryAdapter implements CategoryReadPort, CategorySavePort {

    private final CategoryRepository categoryRepository;
    private final CategoryEntityToDomainMapper categoryEntityToDomainMapper;
    private final CategoryDomainToEntityMapper categoryDomainToEntityMapper;

    @Override
    public List<Category> findAll() {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();

        return categoryEntityToDomainMapper.map(categoryEntities);
    }

    @Override
    public Optional<Category> findById(Long id) {
        Optional<CategoryEntity> categoryEntity = categoryRepository.findById(id);

        return categoryEntity.map(categoryEntityToDomainMapper::map);
    }

    @Override
    public Category save(Category category) {
        CategoryEntity categoryEntity = categoryDomainToEntityMapper.map(category);
        CategoryEntity savedCategory = categoryRepository.save(categoryEntity);

        return categoryEntityToDomainMapper.map(savedCategory);
    }

}
