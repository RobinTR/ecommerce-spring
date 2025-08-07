package org.robn.ecommerce.brand.model.mapper;

import org.mapstruct.Mapper;
import org.robn.ecommerce.brand.model.Brand;
import org.robn.ecommerce.brand.model.response.BrandResponse;
import org.robn.ecommerce.common.mapper.BaseMapper;

@Mapper(componentModel = "spring")
public interface BrandToResponseMapper extends BaseMapper<Brand, BrandResponse> {
}
