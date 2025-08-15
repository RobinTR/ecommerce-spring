package org.robn.ecommerce.address.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.address.model.request.CustomerAddressCreateRequest;
import org.robn.ecommerce.address.service.CustomerAddressService;
import org.robn.ecommerce.common.model.response.EcoBaseResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer-addresses")
public class CustomerAddressController {

    private final CustomerAddressService customerAddressService;

    @PostMapping
    public EcoBaseResponse<Void> create(@RequestBody @Valid  final CustomerAddressCreateRequest customerAddressCreateRequest) {
        customerAddressService.create(customerAddressCreateRequest);

        return EcoBaseResponse.success();
    }

}
