package org.robn.ecommerce.auth.util;

import lombok.experimental.UtilityClass;
import org.robn.ecommerce.auth.exception.EcoAuthFailException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.UUID;

@UtilityClass
public class EcoSecurityUtil {

    public static UUID getCurrentUserId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getPrincipal() == null) {
            throw EcoAuthFailException.of();
        }

        return (UUID) authentication.getPrincipal();
    }

}
