package org.robn.ecommerce.customer.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.auth.mapper.EcoTokenToResponseMapper;
import org.robn.ecommerce.auth.model.EcoToken;
import org.robn.ecommerce.auth.model.response.EcoTokenResponse;
import org.robn.ecommerce.common.model.response.EcoBaseResponse;
import org.robn.ecommerce.customer.model.request.CustomerRegisterRequest;
import org.robn.ecommerce.customer.service.CustomerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final EcoTokenToResponseMapper ecoTokenToResponseMapper;

    @PostMapping("/register")
    public EcoBaseResponse<EcoTokenResponse> create(@RequestBody @Valid final CustomerRegisterRequest request, @RequestHeader("X-DEVICE-ID") final String deviceId) {
        final EcoToken ecoToken = customerService.register(request, deviceId);
        final EcoTokenResponse response = ecoTokenToResponseMapper.map(ecoToken);

        return EcoBaseResponse.successOf(response);
    }

}
