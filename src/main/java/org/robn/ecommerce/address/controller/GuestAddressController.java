package org.robn.ecommerce.address.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.address.model.GuestAddress;
import org.robn.ecommerce.address.model.mapper.GuestAddressDomainToListResponseMapper;
import org.robn.ecommerce.address.model.mapper.GuestAddressDomainToResponseMapper;
import org.robn.ecommerce.address.model.request.GuestAddressCreateRequest;
import org.robn.ecommerce.address.model.request.GuestAddressUpdateRequest;
import org.robn.ecommerce.address.model.response.GuestAddressListResponse;
import org.robn.ecommerce.address.model.response.GuestAddressResponse;
import org.robn.ecommerce.address.service.GuestAddressService;
import org.robn.ecommerce.common.model.response.EcoBaseResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/guest-addresses")
public class GuestAddressController {

    private final GuestAddressService guestAddressService;
    private final GuestAddressDomainToListResponseMapper guestAddressDomainToListResponseMapper;
    private final GuestAddressDomainToResponseMapper guestAddressDomainToResponseMapper;

    @GetMapping("/session/{sessionId}")
    @PreAuthorize("hasRole('ADMIN') or isAnonymous()")
    public EcoBaseResponse<List<GuestAddressListResponse>> findAllBySessionId(@PathVariable final String sessionId) {
        final List<GuestAddress> guestAddresses = guestAddressService.findAllBySessionId(sessionId);
        final List<GuestAddressListResponse> response = guestAddressDomainToListResponseMapper.map(guestAddresses);

        return EcoBaseResponse.successOf(response);
    }

    @GetMapping("/{addressId}")
    @PreAuthorize("hasRole('ADMIN') or isAnonymous()")
    public EcoBaseResponse<GuestAddressResponse> findByAddressId(@PathVariable final UUID addressId) {
        final GuestAddress guestAddress = guestAddressService.findByAddressId(addressId);
        final GuestAddressResponse response = guestAddressDomainToResponseMapper.map(guestAddress);

        return EcoBaseResponse.successOf(response);
    }

    @PostMapping()
    @PreAuthorize("isAnonymous()")
    public EcoBaseResponse<GuestAddressResponse> create(@RequestHeader("Device-ID") final String deviceId, @RequestBody @Valid final GuestAddressCreateRequest guestAddressCreateRequest) {
        final GuestAddress guestAddress = guestAddressService.create(deviceId, guestAddressCreateRequest);
        final GuestAddressResponse response = guestAddressDomainToResponseMapper.map(guestAddress);

        return EcoBaseResponse.successOf(response);
    }

    @PutMapping("/{addressId}")
    @PreAuthorize("hasRole('ADMIN') or isAnonymous()")
    public EcoBaseResponse<GuestAddressResponse> update(@PathVariable final UUID addressId, @RequestBody @Valid final GuestAddressUpdateRequest guestAddressUpdateRequest) {
        final GuestAddress guestAddress = guestAddressService.update(addressId, guestAddressUpdateRequest);
        final GuestAddressResponse response = guestAddressDomainToResponseMapper.map(guestAddress);

        return EcoBaseResponse.successOf(response);
    }

}
