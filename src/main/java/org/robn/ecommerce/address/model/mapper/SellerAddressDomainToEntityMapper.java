package org.robn.ecommerce.address.model.mapper;

import org.mapstruct.Mapper;
import org.robn.ecommerce.address.model.SellerAddress;
import org.robn.ecommerce.address.model.entity.SellerAddressEntity;
import org.robn.ecommerce.common.mapper.BaseMapper;

@Mapper(componentModel = "spring")
public interface SellerAddressDomainToEntityMapper extends BaseMapper<SellerAddress, SellerAddressEntity> {
}
