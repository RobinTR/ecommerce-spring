package org.robn.ecommerce.product.model.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.robn.ecommerce.common.util.validation.name.NameWithNumber;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductUpdateRequest {

    @Positive
    private Long brandId;

    @NameWithNumber
    @Size(max = 100)
    private String name;

    @Size(max = 2000)
    private String description;

    @DecimalMin(value = "10")
    @Digits(integer = 17, fraction = 2)
    private BigDecimal price;

    private Boolean isActive;

    private Boolean isVerified;

}
