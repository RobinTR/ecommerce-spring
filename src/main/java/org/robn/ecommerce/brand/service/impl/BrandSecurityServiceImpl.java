package org.robn.ecommerce.brand.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.auth.exception.EcoAccessDeniedException;
import org.robn.ecommerce.auth.model.enums.Role;
import org.robn.ecommerce.auth.port.SecurityReadPort;
import org.robn.ecommerce.brand.service.BrandSecurityService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandSecurityServiceImpl implements BrandSecurityService {

    private final SecurityReadPort securityReadPort;

    @Override
    public void requireAdminAuthentication() {
        if (!isAdmin()) {
            throw EcoAccessDeniedException.of();
        }
    }

    private boolean isAdmin() {
        return securityReadPort.hasRole(Role.ADMIN);
    }

}
