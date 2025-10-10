package org.robn.ecommerce.cart.port.adapter;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.cart.model.mapper.CustomerCartDomainToEntityMapper;
import org.robn.ecommerce.cart.model.mapper.CustomerCartEntityToDomainMapper;
import org.robn.ecommerce.cart.model.CustomerCart;
import org.robn.ecommerce.cart.model.entity.CustomerCartEntity;
import org.robn.ecommerce.cart.model.enums.CartStatus;
import org.robn.ecommerce.cart.port.CustomerCartReadPort;
import org.robn.ecommerce.cart.port.CustomerCartSavePort;
import org.robn.ecommerce.cart.repository.CustomerCartRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CustomerCartAdapter implements CustomerCartReadPort, CustomerCartSavePort {

    private final CustomerCartRepository customerCartRepository;
    private final CustomerCartEntityToDomainMapper entityToDomainMapper;
    private final CustomerCartDomainToEntityMapper domainToEntityMapper;

    @Override
    public List<CustomerCart> findAllByCustomerId(final UUID customerId) {
        final List<CustomerCartEntity> customerCartEntities = customerCartRepository.findAllByCustomerId(customerId);

        return entityToDomainMapper.map(customerCartEntities);
    }

    @Override
    public List<CustomerCart> findAllByCustomerIdAndCartStatus(final UUID customerId, final CartStatus cartStatus) {
        final List<CustomerCartEntity> customerCartEntities = customerCartRepository.findAllByCustomerIdAndCartStatus(customerId, cartStatus);

        return entityToDomainMapper.map(customerCartEntities);
    }

    @Override
    public Optional<CustomerCart> findByCartId(final UUID cartId) {
        final Optional<CustomerCartEntity> customerCartEntity = customerCartRepository.findById(cartId);

        return customerCartEntity.map(entityToDomainMapper::map);
    }

    @Override
    public boolean existsByIdAndCustomerId(final UUID cartId, final UUID customerId) {
        return customerCartRepository.existsByIdAndCustomerId(cartId, customerId);
    }

    @Override
    public CustomerCart save(final CustomerCart customerCart) {
        final CustomerCartEntity customerCartEntity = domainToEntityMapper.map(customerCart);
        final CustomerCartEntity savedEntity = customerCartRepository.save(customerCartEntity);

        return entityToDomainMapper.map(savedEntity);
    }

}
