package org.robn.ecommerce.product.model.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductImageCreateRequest {

    @NotNull
    @Positive
    private Long productId;

    @NotBlank
    @Size(max = 100)
    private String publicId;

    @NotBlank
    @Size(max = 500)
    private String imageUrl;

    @NotNull
    @Min(value = 1)
    @Max(value = 10000)
    private Integer width;

    @NotNull
    @Min(value = 1)
    @Max(value = 10000)
    private Integer height;

    @NotNull
    @Min(value = 1)
    @Max(value = 5242880)
    private Long sizeBytes;

    @Size(max = 200)
    private String altText;

}
