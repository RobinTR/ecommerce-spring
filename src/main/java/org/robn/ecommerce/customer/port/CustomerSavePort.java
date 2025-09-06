package org.robn.ecommerce.customer.port;

import org.robn.ecommerce.customer.model.Customer;

public interface CustomerSavePort {

    Customer save(Customer customer);

}
