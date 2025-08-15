package org.robn.ecommerce.address.model.mapper;

import org.mapstruct.Mapper;
import org.robn.ecommerce.address.model.Address;
import org.robn.ecommerce.address.model.entity.AddressEntity;
import org.robn.ecommerce.common.mapper.BaseMapper;

@Mapper(componentModel = "spring")
public interface AddressDomainToEntityMapper extends BaseMapper<Address, AddressEntity> {
}
