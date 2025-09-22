package org.robn.ecommerce.cart.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.robn.ecommerce.cart.model.CartItem;
import org.robn.ecommerce.cart.model.CartItemSnapshot;
import org.robn.ecommerce.common.mapper.BaseMapper;

@Mapper(componentModel = "spring")
public interface CartItemToCartItemSnapshotMapper extends BaseMapper<CartItem, CartItemSnapshot> {

    @Override
    @Mapping(target = "id", ignore = true)
    CartItemSnapshot map(CartItem source);

}
