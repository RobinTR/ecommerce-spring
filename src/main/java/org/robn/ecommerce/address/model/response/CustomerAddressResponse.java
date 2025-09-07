package org.robn.ecommerce.address.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerAddressResponse extends AddressResponse {

    private String customerId;

}
