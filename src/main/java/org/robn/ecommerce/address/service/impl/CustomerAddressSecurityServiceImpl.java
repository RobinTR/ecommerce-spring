package org.robn.ecommerce.address.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.address.exception.CustomerAddressNotFoundException;
import org.robn.ecommerce.address.model.CustomerAddress;
import org.robn.ecommerce.address.port.CustomerAddressReadPort;
import org.robn.ecommerce.address.service.CustomerAddressSecurityService;
import org.robn.ecommerce.auth.exception.EcoAccessDeniedException;
import org.robn.ecommerce.auth.model.enums.Role;
import org.robn.ecommerce.auth.port.SecurityReadPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerAddressSecurityServiceImpl implements CustomerAddressSecurityService {

    private final CustomerAddressReadPort customerAddressReadPort;
    private final SecurityReadPort securityReadPort;

    @Override
    public void checkOwnershipByAddressId(final UUID addressId) {
        final CustomerAddress customerAddress = customerAddressReadPort.findByAddressId(addressId)
                .orElseThrow(() -> CustomerAddressNotFoundException.of(addressId));

        if (!isAdmin() && !isOwner(customerAddress)) {
            throw EcoAccessDeniedException.of();
        }
    }

    @Override
    public void checkOwnershipByCustomerId(final UUID customerId) {
        if (!isAdmin() && !securityReadPort.getCurrentUserId().equals(customerId)) {
            throw EcoAccessDeniedException.of();
        }
    }

    private boolean isAdmin() {
        return securityReadPort.hasRole(Role.ADMIN);
    }

    private boolean isOwner(final CustomerAddress address) {
        return address.isOwnedBy(securityReadPort.getCurrentUserId());
    }

}
