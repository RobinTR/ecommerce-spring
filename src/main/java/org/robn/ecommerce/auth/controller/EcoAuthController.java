package org.robn.ecommerce.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.auth.mapper.EcoUserToResponseMapper;
import org.robn.ecommerce.auth.model.EcoUser;
import org.robn.ecommerce.auth.model.request.EcoUserCreateRequest;
import org.robn.ecommerce.auth.model.request.EcoUserLoginRequest;
import org.robn.ecommerce.auth.model.response.EcoUserResponse;
import org.robn.ecommerce.auth.service.EcoUserService;
import org.robn.ecommerce.common.model.response.EcoBaseResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class EcoAuthController {

    private final EcoUserService ecoUserService;
    private final EcoUserToResponseMapper ecoUserToResponseMapper;

    @PostMapping("/register")
    public EcoBaseResponse<EcoUserResponse> create(@RequestBody @Valid final EcoUserCreateRequest ecoUserCreateRequest) {
        final EcoUser ecoUser = ecoUserService.register(ecoUserCreateRequest);
        final EcoUserResponse response = ecoUserToResponseMapper.map(ecoUser);

        return EcoBaseResponse.successOf(response);
    }

    @PostMapping("/login")
    public EcoBaseResponse<EcoUserResponse> login(@RequestBody @Valid final EcoUserLoginRequest ecoUserLoginRequest) {
        final EcoUser ecoUser = ecoUserService.login(ecoUserLoginRequest);
        final EcoUserResponse response = ecoUserToResponseMapper.map(ecoUser);

        return EcoBaseResponse.successOf(response);
    }

}
