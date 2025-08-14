package org.robn.ecommerce.productcategory.repository;

import org.robn.ecommerce.category.model.entity.CategoryEntity;
import org.robn.ecommerce.productcategory.model.entity.ProductCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategoryEntity, Long> {

    void deleteByProductIdAndCategoryId(Long productId, Long categoryId);

    @Query("SELECT c FROM ProductCategoryEntity pc " +
            "JOIN pc.category c " +
            "WHERE pc.productId = :productId " +
            "AND (c.deleted = false OR c.deleted IS NULL)")
    List<CategoryEntity> findCategoriesByProductId(Long productId);

}
