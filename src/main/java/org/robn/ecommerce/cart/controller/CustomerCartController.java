package org.robn.ecommerce.cart.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.cart.mapper.CustomerCartToResponseMapper;
import org.robn.ecommerce.cart.model.CustomerCart;
import org.robn.ecommerce.cart.model.request.AddToCartRequest;
import org.robn.ecommerce.cart.model.response.CustomerCartResponse;
import org.robn.ecommerce.cart.service.CustomerCartService;
import org.robn.ecommerce.common.model.response.EcoBaseResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/carts")
public class CustomerCartController {

    private final CustomerCartService customerCartService;
    private final CustomerCartToResponseMapper customerCartToResponseMapper;

    @PostMapping("/addCustomerCart")
    @PreAuthorize("hasRole('CUSTOMER')")
    public EcoBaseResponse<CustomerCartResponse> addToCart(@RequestBody @Valid final AddToCartRequest addToCartRequest) {
        final CustomerCart customerCart = customerCartService.create(addToCartRequest);
        final CustomerCartResponse response = customerCartToResponseMapper.map(customerCart);

        return EcoBaseResponse.successOf(response);
    }

}
