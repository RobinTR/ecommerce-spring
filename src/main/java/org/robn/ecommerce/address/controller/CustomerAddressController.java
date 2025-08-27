package org.robn.ecommerce.address.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.address.model.CustomerAddress;
import org.robn.ecommerce.address.model.mapper.CustomerAddressDomainToListResponseMapper;
import org.robn.ecommerce.address.model.mapper.CustomerAddressDomainToResponseMapper;
import org.robn.ecommerce.address.model.request.CustomerAddressCreateRequest;
import org.robn.ecommerce.address.model.request.CustomerAddressUpdateRequest;
import org.robn.ecommerce.address.model.response.CustomerAddressListResponse;
import org.robn.ecommerce.address.model.response.CustomerAddressResponse;
import org.robn.ecommerce.address.service.CustomerAddressService;
import org.robn.ecommerce.common.model.response.EcoBaseResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer-addresses")
public class CustomerAddressController {

    private final CustomerAddressService customerAddressService;
    private final CustomerAddressDomainToListResponseMapper customerAddressDomainToListResponseMapper;
    private final CustomerAddressDomainToResponseMapper customerAddressDomainToResponseMapper;

    @GetMapping("/customer/{customerId}")
    @PreAuthorize("hasRole('ADMIN') or authentication.principal == #customerId")
    public EcoBaseResponse<List<CustomerAddressListResponse>> findAllByCustomerId(@PathVariable final UUID customerId) {
        final List<CustomerAddress> customerAddresses = customerAddressService.findAllByCustomerId(customerId);
        final List<CustomerAddressListResponse> response = customerAddressDomainToListResponseMapper.map(customerAddresses);

        return EcoBaseResponse.successOf(response);
    }

    @GetMapping("/{addressId}")
    public EcoBaseResponse<CustomerAddressResponse> findByAddressId(@PathVariable final UUID addressId) {
        CustomerAddress customerAddress = customerAddressService.findByAddressId(addressId);
        CustomerAddressResponse response = customerAddressDomainToResponseMapper.map(customerAddress);

        return EcoBaseResponse.successOf(response);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    public EcoBaseResponse<Void> create(@RequestBody @Valid final CustomerAddressCreateRequest customerAddressCreateRequest) {
        customerAddressService.create(customerAddressCreateRequest);

        return EcoBaseResponse.success();
    }

    @PutMapping("/{addressId}")
    public EcoBaseResponse<Void> update(@PathVariable final UUID addressId, @RequestBody @Valid final CustomerAddressUpdateRequest customerAddressUpdateRequest) {
        customerAddressService.update(addressId, customerAddressUpdateRequest);

        return EcoBaseResponse.success();
    }

}
