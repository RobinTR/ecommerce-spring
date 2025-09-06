package org.robn.ecommerce.seller.port.adapter;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.seller.model.Seller;
import org.robn.ecommerce.seller.model.entity.SellerEntity;
import org.robn.ecommerce.seller.model.mapper.SellerEntityToDomainMapper;
import org.robn.ecommerce.seller.model.mapper.SellerToEntityMapper;
import org.robn.ecommerce.seller.port.SellerReadPort;
import org.robn.ecommerce.seller.port.SellerSavePort;
import org.robn.ecommerce.seller.repository.SellerRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SellerAdapter implements SellerReadPort, SellerSavePort {

    private final SellerRepository sellerRepository;
    private final SellerEntityToDomainMapper sellerEntityToDomainMapper;
    private final SellerToEntityMapper sellerToEntityMapper;

    @Override
    public Seller save(final Seller seller) {
        final SellerEntity sellerEntity = sellerToEntityMapper.map(seller);
        final SellerEntity savedSellerEntity = sellerRepository.save(sellerEntity);

        return sellerEntityToDomainMapper.map(savedSellerEntity);
    }

    @Override
    public Optional<Seller> findByEmail(final String email) {
        final Optional<SellerEntity> sellerEntity = sellerRepository.findByEmail(email);

        return sellerEntity.map(sellerEntityToDomainMapper::map);
    }

}
