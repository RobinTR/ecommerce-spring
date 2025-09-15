package org.robn.ecommerce.guest.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Builder
public record GuestCreateRequest(

        @NotBlank
        @Length(max = 255)
        String deviceId

) {
}
