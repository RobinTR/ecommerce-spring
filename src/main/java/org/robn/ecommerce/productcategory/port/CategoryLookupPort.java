package org.robn.ecommerce.productcategory.port;

import org.robn.ecommerce.category.model.Category;

import java.util.List;

public interface CategoryLookupPort {

    List<Category> findAllCategoriesByProductId(Long productId);

}
