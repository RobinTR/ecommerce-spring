package org.robn.ecommerce.address.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuestAddressListResponse extends AddressResponse {

    private String sessionId;

}
