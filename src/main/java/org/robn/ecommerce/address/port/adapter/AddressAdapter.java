package org.robn.ecommerce.address.port.adapter;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.address.model.Address;
import org.robn.ecommerce.address.model.entity.AddressEntity;
import org.robn.ecommerce.address.model.mapper.AddressDomainToEntityMapper;
import org.robn.ecommerce.address.model.mapper.AddressEntityToDomainMapper;
import org.robn.ecommerce.address.port.AddressReadPort;
import org.robn.ecommerce.address.port.AddressSavePort;
import org.robn.ecommerce.address.repository.AddressRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AddressAdapter implements AddressSavePort, AddressReadPort {

    private final AddressRepository addressRepository;
    private final AddressDomainToEntityMapper addressDomainToEntityMapper;
    private final AddressEntityToDomainMapper addressEntityToDomainMapper;

    @Override
    public Address save(final Address address) {
        final AddressEntity addressEntity = addressDomainToEntityMapper.map(address);
        final AddressEntity savedAddressEntity = addressRepository.save(addressEntity);

        return addressEntityToDomainMapper.map(savedAddressEntity);
    }

    @Override
    public Optional<Address> findById(final UUID id) {
        final Optional<AddressEntity> addressEntity = addressRepository.findById(id);

        return addressEntity.map(addressEntityToDomainMapper::map);
    }

}
