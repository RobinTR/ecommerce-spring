package org.robn.ecommerce.cart.mapper;

import org.mapstruct.Mapper;
import org.robn.ecommerce.cart.model.Cart;
import org.robn.ecommerce.cart.model.entity.CartEntity;
import org.robn.ecommerce.common.mapper.BaseMapper;

@Mapper(componentModel = "spring")
public interface CartDomainToEntityMapper extends BaseMapper<Cart, CartEntity> {
}
