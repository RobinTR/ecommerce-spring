package org.robn.ecommerce.address.port.adapter;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.address.model.CustomerAddress;
import org.robn.ecommerce.address.model.entity.CustomerAddressEntity;
import org.robn.ecommerce.address.model.mapper.CustomerAddressDomainToEntityMapper;
import org.robn.ecommerce.address.model.mapper.CustomerAddressEntityToDomainMapper;
import org.robn.ecommerce.address.port.CustomerAddressReadPort;
import org.robn.ecommerce.address.port.CustomerAddressSavePort;
import org.robn.ecommerce.address.repository.CustomerAddressRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CustomerAddressAdapter implements CustomerAddressReadPort, CustomerAddressSavePort {

    private final CustomerAddressRepository customerAddressRepository;
    private final CustomerAddressEntityToDomainMapper customerAddressEntityToDomainMapper;
    private final CustomerAddressDomainToEntityMapper customerAddressDomainToEntityMapper;

    @Override
    public List<CustomerAddress> findByCustomerId(final UUID customerId) {
        final List<CustomerAddressEntity> customerAddressEntities = customerAddressRepository.findByCustomerId(customerId);

        return customerAddressEntityToDomainMapper.map(customerAddressEntities);
    }

    @Override
    public Optional<CustomerAddress> findByCustomerIdAndAddressId(final UUID customerId, final UUID addressId) {
        final Optional<CustomerAddressEntity> customerAddressEntity = customerAddressRepository.findByCustomerIdAndId(customerId, addressId);

        return customerAddressEntity.map(customerAddressEntityToDomainMapper::map);
    }

    @Override
    public CustomerAddress save(final CustomerAddress customerAddress) {
        final CustomerAddressEntity customerAddressEntity = customerAddressDomainToEntityMapper.map(customerAddress);
        final CustomerAddressEntity savedEntity = customerAddressRepository.save(customerAddressEntity);

        return customerAddressEntityToDomainMapper.map(savedEntity);
    }

}
