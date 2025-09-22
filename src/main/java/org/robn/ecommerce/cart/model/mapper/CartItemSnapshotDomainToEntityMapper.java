package org.robn.ecommerce.cart.model.mapper;

import org.mapstruct.Mapper;
import org.robn.ecommerce.cart.model.CartItemSnapshot;
import org.robn.ecommerce.cart.model.entity.CartItemSnapshotEntity;
import org.robn.ecommerce.common.mapper.BaseMapper;

@Mapper(componentModel = "spring")
public interface CartItemSnapshotDomainToEntityMapper extends BaseMapper<CartItemSnapshot, CartItemSnapshotEntity> {
}
