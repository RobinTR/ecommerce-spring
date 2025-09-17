package org.robn.ecommerce.customer.service;

import org.robn.ecommerce.auth.model.EcoToken;
import org.robn.ecommerce.customer.model.request.CustomerRegisterRequest;

public interface CustomerService {

    EcoToken register(CustomerRegisterRequest request, String deviceId);

}
