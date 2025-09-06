package org.robn.ecommerce.customer.port;

import org.robn.ecommerce.customer.model.Customer;

import java.util.Optional;

public interface CustomerReadPort {

    Optional<Customer> findByEmail(String email);

}
