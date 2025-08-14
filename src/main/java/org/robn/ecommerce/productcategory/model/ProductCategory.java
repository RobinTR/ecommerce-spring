package org.robn.ecommerce.productcategory.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.robn.ecommerce.category.model.Category;
import org.robn.ecommerce.common.model.BaseDomainModel;
import org.robn.ecommerce.product.model.Product;

@Getter
@Setter
@SuperBuilder
public class ProductCategory extends BaseDomainModel {

    private Long id;
    private Long productId;
    private Product product;
    private Long categoryId;
    private Category category;

}
