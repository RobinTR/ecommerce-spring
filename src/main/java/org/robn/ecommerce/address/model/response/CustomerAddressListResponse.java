package org.robn.ecommerce.address.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerAddressListResponse extends AddressResponse {

    private String customerId;

}
