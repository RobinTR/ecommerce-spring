package org.robn.ecommerce.product.model.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProductImageResponse(

        UUID id,
        Long productId,
        String publicId,
        String imageUrl,
        String format,
        Integer width,
        Integer height,
        Long sizeBytes,
        String altText,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {
}
