package org.robn.ecommerce.brand.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.robn.ecommerce.common.util.validation.name.NameWithNumber;

public record BrandUpdateRequest(

        @NotBlank
        @NameWithNumber
        @Size(min = 1, max = 200)
        String name

) {
}
