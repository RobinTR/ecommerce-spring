package org.robn.ecommerce.cart.model.mapper;

import org.mapstruct.Mapper;
import org.robn.ecommerce.cart.model.CartSnapshot;
import org.robn.ecommerce.cart.model.entity.CartSnapshotEntity;
import org.robn.ecommerce.common.mapper.BaseMapper;

@Mapper(componentModel = "spring")
public interface CartSnapshotEntityToDomainMapper extends BaseMapper<CartSnapshotEntity, CartSnapshot> {
}
