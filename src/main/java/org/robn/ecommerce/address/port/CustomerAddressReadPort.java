package org.robn.ecommerce.address.port;

import org.robn.ecommerce.address.model.CustomerAddress;

import java.util.List;
import java.util.UUID;

public interface CustomerAddressReadPort {

    List<CustomerAddress> findByCustomerId(UUID customerId);

}
