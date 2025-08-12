package org.robn.ecommerce.product.model.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ProductImageResponse {

    private UUID id;
    private Long productId;
    private String publicId;
    private String imageUrl;
    private String format;
    private Integer width;
    private Integer height;
    private Long sizeBytes;
    private String altText;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
