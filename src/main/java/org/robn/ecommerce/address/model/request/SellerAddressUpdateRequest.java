package org.robn.ecommerce.address.model.request;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.robn.ecommerce.common.util.validation.PhoneNumber;
import org.robn.ecommerce.common.util.validation.name.Name;
import org.robn.ecommerce.common.util.validation.name.NameWithNumber;
import org.robn.ecommerce.common.util.validation.name.ValidAddress;

@Getter
@Setter
public class SellerAddressUpdateRequest {

    @NameWithNumber
    @Size(min = 2, max = 200)
    private String title;

    @Name
    @Size(min = 2, max = 200)
    private String firstName;

    @Name
    @Size(min = 2, max = 200)
    private String lastName;

    @PhoneNumber
    private String phoneNumber;

    @Name
    @Size(min = 2, max = 200)
    private String city;

    @Name
    @Size(min = 2, max = 200)
    private String district;

    @Name
    @Size(min = 2, max = 200)
    private String neighborhood;

    @ValidAddress
    @Size(min = 2, max = 1000)
    private String fullAddress;

    private Boolean isDefault = false;

}
