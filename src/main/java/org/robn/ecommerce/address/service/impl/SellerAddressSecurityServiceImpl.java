package org.robn.ecommerce.address.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.address.service.SellerAddressSecurityService;
import org.robn.ecommerce.address.service.SellerAddressService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("sellerAddressSecurity")
@RequiredArgsConstructor
public class SellerAddressSecurityServiceImpl implements SellerAddressSecurityService {

    private final SellerAddressService sellerAddressService;

    @Override
    public boolean isOwner(final UUID addressId, final UUID targetSellerId) {
        return sellerAddressService.isAddressBelongsToSeller(addressId, targetSellerId);
    }

}
