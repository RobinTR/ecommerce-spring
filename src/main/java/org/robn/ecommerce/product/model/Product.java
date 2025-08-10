package org.robn.ecommerce.product.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Product {

    private Long id;
    private Long brandId;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean isActive;
    private Boolean isVerified;

}
