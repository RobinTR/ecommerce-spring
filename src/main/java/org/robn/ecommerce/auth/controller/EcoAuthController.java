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
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class EcoAuthController {

    private final EcoAuthService ecoAuthService;
    private final EcoTokenToResponseMapper ecoTokenToResponseMapper;

    @PostMapping("/register")
    public EcoBaseResponse<EcoTokenResponse> create(@RequestBody @Valid final CustomerRegisterRequest customerRegisterRequest, @RequestHeader("X-DEVICE-ID") final String deviceId) {
        final EcoToken ecoToken = ecoAuthService.register(customerRegisterRequest, deviceId);
        final EcoTokenResponse response = ecoTokenToResponseMapper.map(ecoToken);

        return EcoBaseResponse.successOf(response);
    }

    @PostMapping("/register/seller")
    public EcoBaseResponse<EcoTokenResponse> create(@RequestBody @Valid final SellerRegisterRequest sellerRegisterRequest, @RequestHeader("X-DEVICE-ID") final String deviceId) {
        final EcoToken ecoToken = ecoAuthService.register(sellerRegisterRequest, deviceId);
        final EcoTokenResponse response = ecoTokenToResponseMapper.map(ecoToken);

        return EcoBaseResponse.successOf(response);
    }

    @PostMapping("/login")
    public EcoBaseResponse<EcoTokenResponse> login(@RequestBody @Valid final EcoLoginRequest ecoLoginRequest, @RequestHeader("X-DEVICE-ID") final String deviceId) {
        final EcoToken ecoToken = ecoAuthService.login(ecoLoginRequest, deviceId);
        final EcoTokenResponse response = ecoTokenToResponseMapper.map(ecoToken);

        return EcoBaseResponse.successOf(response);
    }

    @PostMapping("/logout")
    public EcoBaseResponse<Void> logout(@RequestBody @Valid final EcoLogoutRequest ecoLogoutRequest, @RequestHeader("X-DEVICE-ID") final String deviceId) {
        ecoAuthService.logout(ecoLogoutRequest, deviceId);

        return EcoBaseResponse.success();
    }

}
