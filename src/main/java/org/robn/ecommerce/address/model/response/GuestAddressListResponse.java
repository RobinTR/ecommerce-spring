package org.robn.ecommerce.address.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuestAddressListResponse {

    private String sessionId;
    private String addressId;
    private String title;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String city;
    private String district;
    private String neighborhood;
    private String fullAddress;
    private Boolean isDefault;

}
