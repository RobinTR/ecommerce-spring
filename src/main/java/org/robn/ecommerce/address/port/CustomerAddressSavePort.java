package org.robn.ecommerce.address.port;

import org.robn.ecommerce.address.model.CustomerAddress;

public interface CustomerAddressSavePort {

    CustomerAddress save(CustomerAddress customerAddress);

}
