package org.robn.ecommerce.category.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.robn.ecommerce.common.util.validation.name.NameWithNumber;

public record CategoryUpdateRequest(

        @NotBlank
        @NameWithNumber
        @Size(min = 1, max = 100)
        String name

) {
}
