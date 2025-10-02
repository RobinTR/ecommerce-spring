package org.robn.ecommerce.address.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.address.exception.CustomerAddressNotFoundException;
import org.robn.ecommerce.address.model.CustomerAddress;
import org.robn.ecommerce.address.port.CustomerAddressReadPort;
import org.robn.ecommerce.address.service.CustomerAddressSecurityService;
import org.robn.ecommerce.auth.exception.EcoAccessDeniedException;
import org.robn.ecommerce.auth.port.SecurityReadPort;
import org.robn.ecommerce.common.service.BaseSecurityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerAddressSecurityServiceImpl implements CustomerAddressSecurityService {

    private final CustomerAddressReadPort customerAddressReadPort;
    private final SecurityReadPort securityReadPort;
    private final BaseSecurityService baseSecurityService;

    @Override
    public void checkAccessByAddressId(final UUID addressId) {
        final CustomerAddress customerAddress = customerAddressReadPort.findByAddressId(addressId)
                .orElseThrow(() -> CustomerAddressNotFoundException.of(addressId));

        if (!baseSecurityService.isAdmin() && !this.isOwner(customerAddress)) {
            throw EcoAccessDeniedException.of();
        }
    }

    @Override
    public void checkAccessByCustomerId(final UUID customerId) {
        if (!baseSecurityService.isAdmin() && !baseSecurityService.isCurrentUser(customerId)) {
            throw EcoAccessDeniedException.of();
        }
    }

    @Override
    public void requireCustomerAuthentication() {
        baseSecurityService.requireCustomerAuthentication();
    }

    private boolean isOwner(final CustomerAddress address) {
        return address.isOwnedBy(securityReadPort.getCurrentUserId());
    }

}
