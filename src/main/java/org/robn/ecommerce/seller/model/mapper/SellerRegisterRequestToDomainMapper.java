package org.robn.ecommerce.seller.model.mapper;

import org.mapstruct.Mapper;
import org.robn.ecommerce.common.mapper.BaseMapper;
import org.robn.ecommerce.seller.model.Seller;
import org.robn.ecommerce.seller.model.request.SellerRegisterRequest;

@Mapper(componentModel = "spring")
public interface SellerRegisterRequestToDomainMapper extends BaseMapper<SellerRegisterRequest, Seller> {
}
