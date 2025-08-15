package org.robn.ecommerce.productcategory.port;

import org.robn.ecommerce.category.model.Category;
import org.robn.ecommerce.product.model.Product;

import java.util.List;

public interface ProductCategoryReadPort {

    List<Category> findAllCategoriesByProductId(Long productId);

    List<Product> findAllProductsByCategoryId(Long categoryId);

}
