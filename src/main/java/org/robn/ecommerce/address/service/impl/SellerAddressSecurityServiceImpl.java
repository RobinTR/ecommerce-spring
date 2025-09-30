package org.robn.ecommerce.address.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.address.exception.SellerAddressNotFoundException;
import org.robn.ecommerce.address.model.SellerAddress;
import org.robn.ecommerce.address.port.SellerAddressReadPort;
import org.robn.ecommerce.address.service.SellerAddressSecurityService;
import org.robn.ecommerce.auth.exception.EcoAccessDeniedException;
import org.robn.ecommerce.auth.port.SecurityReadPort;
import org.robn.ecommerce.common.service.BaseSecurityService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SellerAddressSecurityServiceImpl implements SellerAddressSecurityService {

    private final SellerAddressReadPort sellerAddressReadPort;
    private final SecurityReadPort securityReadPort;
    private final BaseSecurityService baseSecurityService;

    @Override
    public void checkAccessByAddressId(final UUID addressId) {
        final SellerAddress sellerAddress = sellerAddressReadPort.findByAddressId(addressId)
                .orElseThrow(() -> SellerAddressNotFoundException.of(addressId));

        if (!baseSecurityService.isAdmin() && !isOwner(sellerAddress)) {
            throw EcoAccessDeniedException.of();
        }
    }

    @Override
    public void checkAccessBySellerId(final UUID sellerId) {
        if (!baseSecurityService.isAdmin() && !securityReadPort.getCurrentUserId().equals(sellerId)) {
            throw EcoAccessDeniedException.of();
        }
    }

    @Override
    public void requireSellerAuthentication() {
        if (!baseSecurityService.isSeller()) {
            throw EcoAccessDeniedException.of();
        }
    }

    private boolean isOwner(final SellerAddress address) {
        return address.isOwnedBy(securityReadPort.getCurrentUserId());
    }

}
