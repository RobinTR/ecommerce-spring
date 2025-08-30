package org.robn.ecommerce.address.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.address.exception.SellerAddressNotFoundException;
import org.robn.ecommerce.address.model.SellerAddress;
import org.robn.ecommerce.address.model.mapper.SellerAddressCreateRequestToDomainMapper;
import org.robn.ecommerce.address.model.mapper.SellerAddressUpdateMapper;
import org.robn.ecommerce.address.model.request.SellerAddressCreateRequest;
import org.robn.ecommerce.address.model.request.SellerAddressUpdateRequest;
import org.robn.ecommerce.address.port.SellerAddressReadPort;
import org.robn.ecommerce.address.port.SellerAddressSavePort;
import org.robn.ecommerce.address.service.SellerAddressService;
import org.robn.ecommerce.auth.util.EcoSecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SellerAddressServiceImpl implements SellerAddressService {

    private final SellerAddressReadPort sellerAddressReadPort;
    private final SellerAddressSavePort sellerAddressSavePort;
    private final SellerAddressCreateRequestToDomainMapper sellerAddressCreateRequestToDomainMapper;
    private final SellerAddressUpdateMapper sellerAddressUpdateMapper;

    @Override
    public List<SellerAddress> findAllBySellerId(final UUID sellerId) {
        return sellerAddressReadPort.findAllBySellerId(sellerId);
    }

    @Override
    public SellerAddress findByAddressId(final UUID addressId) {
        return getSellerAddressById(addressId);
    }

    @Override
    @Transactional
    public void create(final SellerAddressCreateRequest sellerAddressCreateRequest) {
        final SellerAddress sellerAddress = sellerAddressCreateRequestToDomainMapper.map(sellerAddressCreateRequest);
        sellerAddress.setSellerId(EcoSecurityUtil.getCurrentUserId());
        sellerAddressSavePort.save(sellerAddress);
    }

    @Override
    @Transactional
    public void update(final UUID addressId, final SellerAddressUpdateRequest sellerAddressUpdateRequest) {
        final SellerAddress existingAddress = getSellerAddressById(addressId);
        sellerAddressUpdateMapper.update(existingAddress, sellerAddressUpdateRequest);
        sellerAddressSavePort.save(existingAddress);
    }

    @Override
    public boolean isAddressBelongsToSeller(final UUID addressId, final UUID targetSellerId) {
        final SellerAddress sellerAddress = getSellerAddressById(addressId);

        return sellerAddress.getSellerId().equals(targetSellerId);
    }

    private SellerAddress getSellerAddressById(final UUID addressId) {
        return sellerAddressReadPort.findByAddressId(addressId).orElseThrow(() -> SellerAddressNotFoundException.of(addressId));
    }

}
