package org.robn.ecommerce.product.model.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ProductResponse(

        Long id,
        UUID sellerId,
        Long brandId,
        String name,
        String description,
        BigDecimal price,
        Boolean isActive,
        Boolean isVerified,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String createdBy,
        String updatedBy

) {
}
