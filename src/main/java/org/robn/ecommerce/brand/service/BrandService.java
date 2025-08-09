package org.robn.ecommerce.brand.service;

import org.robn.ecommerce.brand.model.Brand;
import org.robn.ecommerce.brand.model.request.BrandCreateRequest;
import org.robn.ecommerce.brand.model.request.BrandUpdateRequest;

import java.util.List;

public interface BrandService {

    List<Brand> findAll();

    Brand findById(Long id);

    void create(BrandCreateRequest brandCreateRequest);

    void update(Long id, BrandUpdateRequest brandUpdateRequest);

}
