package org.robn.ecommerce.address.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.address.model.SellerAddress;
import org.robn.ecommerce.address.model.mapper.SellerAddressDomainToListResponseMapper;
import org.robn.ecommerce.address.model.mapper.SellerAddressDomainToResponseMapper;
import org.robn.ecommerce.address.model.request.SellerAddressCreateRequest;
import org.robn.ecommerce.address.model.request.SellerAddressUpdateRequest;
import org.robn.ecommerce.address.model.response.SellerAddressListResponse;
import org.robn.ecommerce.address.model.response.SellerAddressResponse;
import org.robn.ecommerce.address.service.SellerAddressService;
import org.robn.ecommerce.common.model.response.EcoBaseResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/seller-addresses")
public class SellerAddressController {

    private final SellerAddressService sellerAddressService;
    private final SellerAddressDomainToListResponseMapper sellerAddressDomainToListResponseMapper;
    private final SellerAddressDomainToResponseMapper sellerAddressDomainToResponseMapper;

    @GetMapping("/seller/{sellerId}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('SELLER') and authentication.principal == #sellerId )")
    public EcoBaseResponse<List<SellerAddressListResponse>> findAllBySellerId(@PathVariable final UUID sellerId) {
        final List<SellerAddress> sellerAddresses = sellerAddressService.findAllBySellerId(sellerId);
        final List<SellerAddressListResponse> response = sellerAddressDomainToListResponseMapper.map(sellerAddresses);

        return EcoBaseResponse.successOf(response);
    }

    @GetMapping("/{addressId}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('SELLER') and @sellerAddressService.isAddressBelongsToSeller(#addressId, authentication.principal))")
    public EcoBaseResponse<SellerAddressResponse> findByAddressId(@PathVariable final UUID addressId) {
        final SellerAddress sellerAddress = sellerAddressService.findByAddressId(addressId);
        final SellerAddressResponse response = sellerAddressDomainToResponseMapper.map(sellerAddress);

        return EcoBaseResponse.successOf(response);
    }

    @PostMapping
    @PreAuthorize("hasRole('SELLER')")
    public EcoBaseResponse<Void> create(@RequestBody @Valid final SellerAddressCreateRequest sellerAddressCreateRequest) {
        sellerAddressService.create(sellerAddressCreateRequest);

        return EcoBaseResponse.success();
    }

    @PutMapping("/{addressId}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('SELLER') and @sellerAddressService.isAddressBelongsToSeller(#addressId, authentication.principal))")
    public EcoBaseResponse<Void> update(@PathVariable final UUID addressId, @RequestBody @Valid final SellerAddressUpdateRequest sellerAddressUpdateRequest) {
        sellerAddressService.update(addressId, sellerAddressUpdateRequest);

        return EcoBaseResponse.success();
    }

}
