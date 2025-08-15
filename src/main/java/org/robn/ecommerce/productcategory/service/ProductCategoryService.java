package org.robn.ecommerce.productcategory.service;

import org.robn.ecommerce.category.model.Category;
import org.robn.ecommerce.product.model.Product;
import org.robn.ecommerce.productcategory.model.request.ProductCategoryCreateRequest;

import java.util.List;

public interface ProductCategoryService {

    List<Category> findAllCategoriesByProductId(Long productId);

    List<Product> findAllProductsByCategoryId(Long categoryId);

    void create(ProductCategoryCreateRequest productCategoryCreateRequest);

    void delete(Long productId, Long categoryId);

}
