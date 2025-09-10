package org.robn.ecommerce.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.auth.mapper.EcoTokenToResponseMapper;
import org.robn.ecommerce.auth.model.EcoToken;
import org.robn.ecommerce.auth.model.request.EcoLoginRequest;
import org.robn.ecommerce.auth.model.request.EcoLogoutRequest;
import org.robn.ecommerce.auth.model.response.EcoTokenResponse;
import org.robn.ecommerce.auth.service.EcoAuthService;
import org.robn.ecommerce.common.model.response.EcoBaseResponse;
import org.robn.ecommerce.customer.model.request.CustomerRegisterRequest;
import org.robn.ecommerce.seller.model.request.SellerRegisterRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class EcoAuthController {

    private final EcoAuthService ecoAuthService;
    private final EcoTokenToResponseMapper ecoTokenToResponseMapper;

    @PostMapping("/register")
    public EcoBaseResponse<EcoTokenResponse> create(@RequestBody @Valid final CustomerRegisterRequest customerRegisterRequest) {
        final EcoToken ecoToken = ecoAuthService.register(customerRegisterRequest);
        final EcoTokenResponse response = ecoTokenToResponseMapper.map(ecoToken);

        return EcoBaseResponse.successOf(response);
    }

    @PostMapping("/register/seller")
    public EcoBaseResponse<EcoTokenResponse> create(@RequestBody @Valid final SellerRegisterRequest sellerRegisterRequest) {
        final EcoToken ecoToken = ecoAuthService.register(sellerRegisterRequest);
        final EcoTokenResponse response = ecoTokenToResponseMapper.map(ecoToken);

        return EcoBaseResponse.successOf(response);
    }

    @PostMapping("/login")
    public EcoBaseResponse<EcoTokenResponse> login(@RequestBody @Valid final EcoLoginRequest ecoLoginRequest) {
        final EcoToken ecoToken = ecoAuthService.login(ecoLoginRequest);
        final EcoTokenResponse response = ecoTokenToResponseMapper.map(ecoToken);

        return EcoBaseResponse.successOf(response);
    }

    @PostMapping("/logout")
    public EcoBaseResponse<Void> logout(@RequestBody @Valid final EcoLogoutRequest ecoLogoutRequest) {
        ecoAuthService.logout(ecoLogoutRequest);
        return EcoBaseResponse.success();
    }

}
