package org.robn.ecommerce.product.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.auth.exception.EcoAccessDeniedException;
import org.robn.ecommerce.auth.model.enums.Role;
import org.robn.ecommerce.auth.port.SecurityReadPort;
import org.robn.ecommerce.product.exception.ProductNotFoundException;
import org.robn.ecommerce.product.model.Product;
import org.robn.ecommerce.product.port.ProductReadPort;
import org.robn.ecommerce.product.service.ProductSecurityService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductSecurityServiceImpl implements ProductSecurityService {

    private final ProductReadPort productReadPort;
    private final SecurityReadPort securityReadPort;

    @Override
    public void checkAccessByProductId(final Long productId) {
        final Product product = productReadPort.findById(productId).orElseThrow(() -> ProductNotFoundException.of(productId));

        if (!isAdmin() && !isOwner(product)) {
            throw EcoAccessDeniedException.of();
        }
    }

    private boolean isAdmin() {
        return securityReadPort.hasRole(Role.ADMIN);
    }

    private boolean isOwner(final Product product) {
        return product.isOwnedBy(securityReadPort.getCurrentUserId());
    }

}
