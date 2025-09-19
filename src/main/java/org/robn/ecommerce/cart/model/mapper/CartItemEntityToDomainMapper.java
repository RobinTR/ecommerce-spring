package org.robn.ecommerce.cart.model.mapper;

import org.mapstruct.Mapper;
import org.robn.ecommerce.cart.model.CartItem;
import org.robn.ecommerce.cart.model.entity.CartItemEntity;
import org.robn.ecommerce.common.mapper.BaseMapper;

@Mapper(componentModel = "spring")
public interface CartItemEntityToDomainMapper extends BaseMapper<CartItemEntity, CartItem> {
}
