package org.robn.ecommerce.productcategory.port;

import org.robn.ecommerce.productcategory.model.ProductCategory;

public interface ProductCategorySavePort {

    ProductCategory save(ProductCategory productCategory);

}
