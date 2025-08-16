package org.robn.ecommerce.address.port;

import org.robn.ecommerce.address.model.CustomerAddress;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerAddressReadPort {

    List<CustomerAddress> findAllByCustomerId(UUID customerId);

    Optional<CustomerAddress> findByAddressId(UUID addressId);

}
