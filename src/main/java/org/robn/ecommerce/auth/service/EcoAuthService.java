package org.robn.ecommerce.auth.service;

import org.robn.ecommerce.auth.model.EcoToken;
import org.robn.ecommerce.auth.model.request.EcoLoginRequest;
import org.robn.ecommerce.customer.model.request.CustomerRegisterRequest;
import org.robn.ecommerce.seller.model.request.SellerRegisterRequest;

public interface EcoAuthService {

    EcoToken register(CustomerRegisterRequest customerRegisterRequest);

    EcoToken register(SellerRegisterRequest sellerRegisterRequest);

    EcoToken login(EcoLoginRequest ecoLoginRequest);

}
