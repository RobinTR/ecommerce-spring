package org.robn.ecommerce.seller.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.robn.ecommerce.common.util.validation.EmailAddress;
import org.robn.ecommerce.common.util.validation.Password;
import org.robn.ecommerce.common.util.validation.PhoneNumber;
import org.robn.ecommerce.common.util.validation.name.Name;
import org.robn.ecommerce.common.util.validation.name.NameWithNumber;

public record SellerRegisterRequest(

        @NotBlank
        @Name
        @Size(max = 200)
        String storeName,

        @NotBlank
        @NameWithNumber
        @Size(min = 16, max = 16)
        String mersisNumber,

        @NotBlank
        @PhoneNumber
        String contactNumber,

        @NotBlank
        @EmailAddress
        String email,

        @NotBlank
        @Password
        String password,

        @NotBlank
        @Size(max = 255)
        String deviceId

) {
}
