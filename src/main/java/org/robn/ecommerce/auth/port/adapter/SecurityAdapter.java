package org.robn.ecommerce.auth.port.adapter;

import org.robn.ecommerce.auth.exception.EcoAuthFailException;
import org.robn.ecommerce.auth.model.enums.Role;
import org.robn.ecommerce.auth.port.SecurityReadPort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SecurityAdapter implements SecurityReadPort {

    @Override
    public UUID getCurrentUserId() {
        return (UUID) getAuthentication().getPrincipal();
    }

    @Override
    public boolean hasRole(final Role role) {
        return getAuthentication().getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(role.getAuthority()));
    }

    private Authentication getAuthentication() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getPrincipal() == null) {
            throw EcoAuthFailException.of();
        }

        return authentication;
    }

}
