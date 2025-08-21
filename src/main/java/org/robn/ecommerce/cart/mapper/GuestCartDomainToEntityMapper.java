package org.robn.ecommerce.cart.mapper;

import org.mapstruct.Mapper;
import org.robn.ecommerce.cart.model.GuestCart;
import org.robn.ecommerce.cart.model.entity.GuestCartEntity;
import org.robn.ecommerce.common.mapper.BaseMapper;

@Mapper(componentModel = "spring")
public interface GuestCartDomainToEntityMapper extends BaseMapper<GuestCart, GuestCartEntity> {
}
