package org.robn.ecommerce.product.model.request;

import jakarta.validation.constraints.*;

public record ProductImageCreateRequest(

        @NotNull
        @Positive
        Long productId,

        @NotBlank
        @Size(max = 100)
        String publicId,

        @NotBlank
        @Size(max = 500)
        String imageUrl,

        @NotNull
        @Min(value = 1)
        @Max(value = 10000)
        Integer width,

        @NotNull
        @Min(value = 1)
        @Max(value = 10000)
        Integer height,

        @NotNull
        @Min(value = 1)
        @Max(value = 5242880)
        Long sizeBytes,

        @Size(max = 200)
        String altText

) {
}
