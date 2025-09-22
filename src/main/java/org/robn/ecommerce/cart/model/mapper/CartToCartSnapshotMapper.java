package org.robn.ecommerce.cart.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.robn.ecommerce.cart.model.Cart;
import org.robn.ecommerce.cart.model.CartSnapshot;
import org.robn.ecommerce.common.mapper.BaseMapper;

@Mapper(componentModel = "spring")
public interface CartToCartSnapshotMapper extends BaseMapper<Cart, CartSnapshot> {

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cartId", source = "id")
    CartSnapshot map(Cart source);

}
