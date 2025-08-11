package org.robn.ecommerce.product.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.robn.ecommerce.common.model.BaseDomainModel;

import java.math.BigDecimal;

@Getter
@Setter
@SuperBuilder
public class Product extends BaseDomainModel {

    private Long id;
    private Long brandId;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean isActive;
    private Boolean isVerified;

}
