package org.robn.ecommerce.cart.port.adapter;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.cart.model.mapper.CartDomainToEntityMapper;
import org.robn.ecommerce.cart.model.mapper.CartEntityToDomainMapper;
import org.robn.ecommerce.cart.model.Cart;
import org.robn.ecommerce.cart.model.entity.CartEntity;
import org.robn.ecommerce.cart.model.enums.CartStatus;
import org.robn.ecommerce.cart.port.CartReadPort;
import org.robn.ecommerce.cart.port.CartSavePort;
import org.robn.ecommerce.cart.repository.CartRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CartAdapter implements CartReadPort, CartSavePort {

    private final CartRepository cartRepository;
    private final CartEntityToDomainMapper cartEntityToDomainMapper;
    private final CartDomainToEntityMapper cartDomainToEntityMapper;

    @Override
    public Optional<Cart> findById(final UUID id) {
        final Optional<CartEntity> cartEntity = cartRepository.findById(id);

        return cartEntity.map(cartEntityToDomainMapper::map);
    }

    @Override
    public Optional<Cart> findByIdAndCartStatus(final UUID id, final CartStatus cartStatus) {
        final Optional<CartEntity> cartEntity = cartRepository.findByIdAndCartStatus(id, cartStatus);

        return cartEntity.map(cartEntityToDomainMapper::map);
    }

    @Override
    public Cart save(final Cart cart) {
        final CartEntity cartEntity = cartDomainToEntityMapper.map(cart);
        final CartEntity savedCartEntity = cartRepository.save(cartEntity);

        return cartEntityToDomainMapper.map(savedCartEntity);
    }

}
