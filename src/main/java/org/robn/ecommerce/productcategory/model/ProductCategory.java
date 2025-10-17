package org.robn.ecommerce.productcategory.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.robn.ecommerce.common.model.BaseDomainModel;

@Getter
@Setter
@SuperBuilder
public class ProductCategory extends BaseDomainModel {

    private Long id;
    private Long productId;
    private Long categoryId;

}
