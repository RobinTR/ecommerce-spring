package org.robn.ecommerce.category.repository;

import org.robn.ecommerce.category.model.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
