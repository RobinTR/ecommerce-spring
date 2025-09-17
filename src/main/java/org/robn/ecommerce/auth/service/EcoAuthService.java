package org.robn.ecommerce.auth.service;

import org.robn.ecommerce.auth.model.EcoToken;
import org.robn.ecommerce.auth.model.request.EcoLoginRequest;
import org.robn.ecommerce.auth.model.request.EcoLogoutRequest;
import org.robn.ecommerce.customer.model.request.CustomerRegisterRequest;
import org.robn.ecommerce.seller.model.request.SellerRegisterRequest;

public interface EcoAuthService {

    EcoToken register(CustomerRegisterRequest customerRegisterRequest, String deviceId);

    EcoToken register(SellerRegisterRequest sellerRegisterRequest, String deviceId);

    EcoToken login(EcoLoginRequest ecoLoginRequest, String deviceId);

    void logout(EcoLogoutRequest ecoLogoutRequest, String deviceId);

}
