package org.robn.ecommerce.product.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.product.service.ProductSecurityService;
import org.robn.ecommerce.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("productSecurity")
@RequiredArgsConstructor
public class ProductSecurityServiceImpl implements ProductSecurityService {

    private final ProductService productService;

    @Override
    public boolean isOwner(final Long productId, final UUID sellerId) {
        return productService.isProductOwnedBySeller(productId, sellerId);
    }

}
