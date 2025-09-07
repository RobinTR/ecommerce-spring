package org.robn.ecommerce.brand.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.brand.exception.BrandNotFoundException;
import org.robn.ecommerce.brand.model.Brand;
import org.robn.ecommerce.brand.model.mapper.BrandCreateRequestToDomainMapper;
import org.robn.ecommerce.brand.model.request.BrandCreateRequest;
import org.robn.ecommerce.brand.model.request.BrandUpdateRequest;
import org.robn.ecommerce.brand.port.BrandReadPort;
import org.robn.ecommerce.brand.port.BrandSavePort;
import org.robn.ecommerce.brand.service.BrandService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BrandServiceImpl implements BrandService {

    private final BrandReadPort brandReadPort;
    private final BrandSavePort brandSavePort;
    private final BrandCreateRequestToDomainMapper brandCreateRequestToDomainMapper;

    @Override
    public List<Brand> findAll() {
        return brandReadPort.findAll();
    }

    @Override
    public Brand findById(final Long id) {
        return getExistingBrand(id);
    }

    @Override
    @Transactional
    public void create(final BrandCreateRequest brandCreateRequest) {
        final Brand brand = brandCreateRequestToDomainMapper.map(brandCreateRequest);
        brandSavePort.save(brand);
    }

    @Override
    @Transactional
    public void update(final Long id, final BrandUpdateRequest brandUpdateRequest) {
        final Brand brand = getExistingBrand(id);
        brand.setName(brandUpdateRequest.name());
        brandSavePort.save(brand);
    }

    private Brand getExistingBrand(final Long id) {
        return brandReadPort.findById(id).orElseThrow(() -> BrandNotFoundException.of(id));
    }

}
