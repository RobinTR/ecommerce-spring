package org.robn.ecommerce.product.model.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ProductResponse {

    private Long id;
    private Long brandId;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean isActive;
    private Boolean isVerified;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;

}
