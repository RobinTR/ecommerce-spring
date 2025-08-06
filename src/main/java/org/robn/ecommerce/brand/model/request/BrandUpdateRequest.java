package org.robn.ecommerce.brand.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.robn.ecommerce.common.util.validation.Name;

@Getter
@Setter
public class BrandUpdateRequest {

    @NotNull
    @Positive
    private Long id;

    @Name
    @NotBlank
    @Size(min = 1, max = 100)
    private String name;

}
