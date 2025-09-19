package org.robn.ecommerce.cart.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.cart.model.CustomerCart;
import org.robn.ecommerce.cart.model.GuestCart;
import org.robn.ecommerce.cart.model.mapper.CustomerCartToResponseMapper;
import org.robn.ecommerce.cart.model.mapper.GuestCartDomainToResponseMapper;
import org.robn.ecommerce.cart.model.request.AddToCartRequest;
import org.robn.ecommerce.cart.model.response.CustomerCartResponse;
import org.robn.ecommerce.cart.model.response.GuestCartResponse;
import org.robn.ecommerce.cart.service.CustomerCartService;
import org.robn.ecommerce.cart.service.GuestCartService;
import org.robn.ecommerce.common.model.response.EcoBaseResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/carts")
public class CartController {

    private final CustomerCartService customerCartService;
    private final CustomerCartToResponseMapper customerCartToResponseMapper;
    private final GuestCartService guestCartService;
    private final GuestCartDomainToResponseMapper guestCartDomainToResponseMapper;

    @PostMapping("/addCustomerCart")
    @PreAuthorize("hasRole('CUSTOMER')")
    public EcoBaseResponse<CustomerCartResponse> addToCustomerCart(@RequestBody @Valid final AddToCartRequest addToCartRequest) {
        final CustomerCart customerCart = customerCartService.create(addToCartRequest);
        final CustomerCartResponse response = customerCartToResponseMapper.map(customerCart);

        return EcoBaseResponse.successOf(response);
    }

    @PostMapping("/addGuestCart")
    @PreAuthorize("isAnonymous()")
    public EcoBaseResponse<GuestCartResponse> addToGuestCart(@RequestHeader("Device-Id") final String deviceId, @RequestBody @Valid final AddToCartRequest addToCartRequest) {
        final GuestCart guestCart = guestCartService.create(deviceId, addToCartRequest);
        final GuestCartResponse response = guestCartDomainToResponseMapper.map(guestCart);

        return EcoBaseResponse.successOf(response);
    }

}
