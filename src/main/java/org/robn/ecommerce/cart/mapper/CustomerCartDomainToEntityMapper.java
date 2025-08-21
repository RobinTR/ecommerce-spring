package org.robn.ecommerce.cart.mapper;

import org.mapstruct.Mapper;
import org.robn.ecommerce.cart.model.CustomerCart;
import org.robn.ecommerce.cart.model.entity.CustomerCartEntity;
import org.robn.ecommerce.common.mapper.BaseMapper;

@Mapper(componentModel = "spring")
public interface CustomerCartDomainToEntityMapper extends BaseMapper<CustomerCart, CustomerCartEntity> {
}
