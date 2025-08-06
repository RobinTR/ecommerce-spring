package org.robn.ecommerce.brand.service;

import org.robn.ecommerce.brand.model.Brand;
import org.robn.ecommerce.brand.model.request.BrandCreateRequest;
import org.robn.ecommerce.brand.model.request.BrandUpdateRequest;

import java.util.List;

public interface BrandService {

    void createBrand(BrandCreateRequest brandCreateRequest);

    void updateBrand(BrandUpdateRequest brandUpdateRequest);

    List<Brand> listBrands();

    Brand findBrandById(Long id);

}
