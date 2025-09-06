package org.robn.ecommerce.address.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.robn.ecommerce.common.util.validation.PhoneNumber;
import org.robn.ecommerce.common.util.validation.name.Name;
import org.robn.ecommerce.common.util.validation.name.NameWithNumber;
import org.robn.ecommerce.common.util.validation.name.ValidAddress;

@Getter
@Setter
public class AddressCreateRequest {

    @NotBlank
    @NameWithNumber
    @Size(min = 2, max = 200)
    private String title;

    @NotBlank
    @Name
    @Size(min = 2, max = 200)
    private String firstName;

    @NotBlank
    @Name
    @Size(min = 2, max = 200)
    private String lastName;

    @NotNull
    @PhoneNumber
    private String phoneNumber;

    @NotBlank
    @Name
    @Size(min = 2, max = 200)
    private String city;

    @NotBlank
    @Name
    @Size(min = 2, max = 200)
    private String district;

    @NotBlank
    @Name
    @Size(min = 2, max = 200)
    private String neighborhood;

    @NotBlank
    @ValidAddress
    @Size(min = 2, max = 1000)
    private String fullAddress;

}
