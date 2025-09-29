package org.robn.ecommerce.common.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.auth.model.enums.Role;
import org.robn.ecommerce.auth.port.SecurityReadPort;
import org.robn.ecommerce.common.service.BaseSecurityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BaseSecurityServiceImpl implements BaseSecurityService {

    private final SecurityReadPort securityReadPort;

    @Override
    public boolean isAdmin() {
        return securityReadPort.hasRole(Role.ADMIN);
    }

    @Override
    public boolean isCustomer() {
        return securityReadPort.hasRole(Role.CUSTOMER);
    }

    @Override
    public boolean isSeller() {
        return securityReadPort.hasRole(Role.SELLER);
    }

    @Override
    public boolean isCurrentUser(final UUID userId) {
        return securityReadPort.getCurrentUserId().equals(userId);
    }

}
