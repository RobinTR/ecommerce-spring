package org.robn.ecommerce.cart.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.auth.exception.EcoAccessDeniedException;
import org.robn.ecommerce.auth.port.SecurityReadPort;
import org.robn.ecommerce.cart.port.CustomerCartReadPort;
import org.robn.ecommerce.cart.service.CartSecurityService;
import org.robn.ecommerce.common.service.BaseSecurityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartSecurityServiceImpl implements CartSecurityService {

    private final SecurityReadPort securityReadPort;
    private final CustomerCartReadPort customerCartReadPort;
    private final BaseSecurityService baseSecurityService;

    @Override
    public void checkAccessByCartId(final UUID cartId) {
        if (baseSecurityService.isAdmin()) {
            return;
        }

        final UUID currentUserId = securityReadPort.getCurrentUserId();

        if (!customerCartReadPort.existsByIdAndCustomerId(cartId, currentUserId)) {
            throw EcoAccessDeniedException.of();
        }
    }

    @Override
    public void requireAdminAuthentication() {
        if (!baseSecurityService.isAdmin()) {
            throw EcoAccessDeniedException.of();
        }
    }

    @Override
    public void requireCustomerAuthentication() {
        if (!baseSecurityService.isCustomer()) {
            throw EcoAccessDeniedException.of();
        }
    }

}
