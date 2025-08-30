package org.robn.ecommerce.address.service;

import org.robn.ecommerce.address.model.SellerAddress;
import org.robn.ecommerce.address.model.request.SellerAddressCreateRequest;
import org.robn.ecommerce.address.model.request.SellerAddressUpdateRequest;

import java.util.List;
import java.util.UUID;

public interface SellerAddressService {

    List<SellerAddress> findAllBySellerId(UUID sellerId);

    SellerAddress findByAddressId(UUID addressId);

    void create(SellerAddressCreateRequest sellerAddressCreateRequest);

    void update(UUID addressId, SellerAddressUpdateRequest sellerAddressUpdateRequest);

    boolean isAddressBelongsToSeller(UUID addressId, UUID targetSellerId);

}
