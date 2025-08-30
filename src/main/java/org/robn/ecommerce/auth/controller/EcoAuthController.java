package org.robn.ecommerce.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.auth.mapper.EcoTokenToResponseMapper;
import org.robn.ecommerce.auth.model.EcoToken;
import org.robn.ecommerce.auth.model.request.EcoUserCreateRequest;
import org.robn.ecommerce.auth.model.request.EcoUserLoginRequest;
import org.robn.ecommerce.auth.model.response.EcoTokenResponse;
import org.robn.ecommerce.auth.service.EcoAuthService;
import org.robn.ecommerce.common.model.response.EcoBaseResponse;
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
    public EcoBaseResponse<EcoTokenResponse> create(@RequestBody @Valid final EcoUserCreateRequest ecoUserCreateRequest) {
        final EcoToken ecoToken = ecoAuthService.register(ecoUserCreateRequest);
        final EcoTokenResponse response = ecoTokenToResponseMapper.map(ecoToken);

        return EcoBaseResponse.successOf(response);
    }

    @PostMapping("/login")
    public EcoBaseResponse<EcoTokenResponse> login(@RequestBody @Valid final EcoUserLoginRequest ecoUserLoginRequest) {
        final EcoToken ecoToken = ecoAuthService.login(ecoUserLoginRequest);
        final EcoTokenResponse response = ecoTokenToResponseMapper.map(ecoToken);

        return EcoBaseResponse.successOf(response);
    }

}
