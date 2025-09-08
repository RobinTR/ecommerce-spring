package org.robn.ecommerce.customer.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.robn.ecommerce.common.util.validation.EmailAddress;
import org.robn.ecommerce.common.util.validation.Password;
import org.robn.ecommerce.common.util.validation.PhoneNumber;
import org.robn.ecommerce.common.util.validation.name.Name;

public record CustomerRegisterRequest(

        @NotBlank
        @Name
        @Size(max = 200)
        String firstName,

        @NotBlank
        @Name
        @Size(max = 200)
        String lastName,

        @NotBlank
        @PhoneNumber
        String phoneNumber,

        @NotBlank
        @EmailAddress
        String email,

        @NotBlank
        @Password
        String password,

        @NotBlank
        String deviceId

) {
}
