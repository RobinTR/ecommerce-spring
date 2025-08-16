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
public class CustomerAddressCreateRequest {

    @NameWithNumber
    @NotBlank
    @Size(min = 2, max = 200)
    private String title;

    @Name
    @NotBlank
    @Size(min = 2, max = 200)
    private String firstName;

    @Name
    @NotBlank
    @Size(min = 2, max = 200)
    private String lastName;

    @NotNull
    @PhoneNumber
    private String phoneNumber;

    @Name
    @NotBlank
    @Size(min = 2, max = 200)
    private String city;

    @Name
    @NotBlank
    @Size(min = 2, max = 200)
    private String district;

    @Name
    @NotBlank
    @Size(min = 2, max = 200)
    private String neighborhood;

    @ValidAddress
    @NotBlank
    @Size(min = 2, max = 1000)
    private String fullAddress;

    private Boolean isDefault = false;

}
