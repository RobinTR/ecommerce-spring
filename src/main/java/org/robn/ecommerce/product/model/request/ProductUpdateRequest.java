package org.robn.ecommerce.product.model.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.robn.ecommerce.common.util.validation.name.NameWithNumber;

import java.math.BigDecimal;

public record ProductUpdateRequest(

        @Positive
        Long brandId,

        @NameWithNumber
        @Size(max = 100)
        String name,

        @Size(max = 2000)
        String description,

        @DecimalMin(value = "10")
        @Digits(integer = 17, fraction = 2)
        BigDecimal price,

        Boolean isActive,

        Boolean isVerified

) {
}
