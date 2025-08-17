package org.robn.ecommerce.address.port.adapter;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.address.model.SellerAddress;
import org.robn.ecommerce.address.model.entity.SellerAddressEntity;
import org.robn.ecommerce.address.model.mapper.SellerAddressDomainToEntityMapper;
import org.robn.ecommerce.address.model.mapper.SellerAddressEntityToDomainMapper;
import org.robn.ecommerce.address.port.SellerAddressReadPort;
import org.robn.ecommerce.address.port.SellerAddressSavePort;
import org.robn.ecommerce.address.repository.SellerAddressRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SellerAddressAdapter implements SellerAddressReadPort, SellerAddressSavePort {

    private final SellerAddressRepository sellerAddressRepository;
    private final SellerAddressEntityToDomainMapper sellerAddressEntityToDomainMapper;
    private final SellerAddressDomainToEntityMapper sellerAddressDomainToEntityMapper;

    @Override
    public List<SellerAddress> findAllBySellerId(final UUID sellerId) {
        final List<SellerAddressEntity> sellerAddressEntities = sellerAddressRepository.findAllBySellerId(sellerId);

        return sellerAddressEntityToDomainMapper.map(sellerAddressEntities);
    }

    @Override
    public Optional<SellerAddress> findByAddressId(final UUID addressId) {
        final Optional<SellerAddressEntity> sellerAddressEntity = sellerAddressRepository.findById(addressId);

        return sellerAddressEntity.map(sellerAddressEntityToDomainMapper::map);
    }

    @Override
    public SellerAddress save(final SellerAddress sellerAddress) {
        final SellerAddressEntity sellerAddressEntity = sellerAddressDomainToEntityMapper.map(sellerAddress);
        final SellerAddressEntity savedSellerAddressEntity = sellerAddressRepository.save(sellerAddressEntity);

        return sellerAddressEntityToDomainMapper.map(savedSellerAddressEntity);
    }

}
