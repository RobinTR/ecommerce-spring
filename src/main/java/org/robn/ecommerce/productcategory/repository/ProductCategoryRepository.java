package org.robn.ecommerce.productcategory.repository;

import org.robn.ecommerce.productcategory.model.entity.ProductCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategoryEntity, Long> {
}
