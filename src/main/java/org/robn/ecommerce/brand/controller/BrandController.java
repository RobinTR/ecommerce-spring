package org.robn.ecommerce.brand.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.brand.model.Brand;
import org.robn.ecommerce.brand.model.mapper.BrandDomainToListResponseMapper;
import org.robn.ecommerce.brand.model.mapper.BrandDomainToResponseMapper;
import org.robn.ecommerce.brand.model.request.BrandCreateRequest;
import org.robn.ecommerce.brand.model.request.BrandUpdateRequest;
import org.robn.ecommerce.brand.model.response.BrandListResponse;
import org.robn.ecommerce.brand.model.response.BrandResponse;
import org.robn.ecommerce.brand.service.BrandService;
import org.robn.ecommerce.common.model.response.EcoBaseResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/brands")
public class BrandController {

    private final BrandService brandService;
    private final BrandDomainToListResponseMapper brandDomainToListResponseMapper;
    private final BrandDomainToResponseMapper brandDomainToResponseMapper;

    @GetMapping
    public EcoBaseResponse<List<BrandListResponse>> findAll() {
        List<Brand> brands = brandService.findAll();
        List<BrandListResponse> brandsResponse = brandDomainToListResponseMapper.map(brands);

        return EcoBaseResponse.successOf(brandsResponse);
    }

    @GetMapping("/{id}")
    public EcoBaseResponse<BrandResponse> findById(@PathVariable Long id) {
        Brand brand = brandService.findById(id);
        BrandResponse brandResponse = brandDomainToResponseMapper.map(brand);

        return EcoBaseResponse.successOf(brandResponse);
    }

    @PostMapping
    public EcoBaseResponse<Void> create(@RequestBody @Valid BrandCreateRequest brandCreateRequest) {
        brandService.create(brandCreateRequest);

        return EcoBaseResponse.success();
    }

    @PutMapping("/{id}")
    public EcoBaseResponse<Void> update(@PathVariable Long id, @RequestBody @Valid BrandUpdateRequest brandUpdateRequest) {
        brandService.update(id, brandUpdateRequest);

        return EcoBaseResponse.success();
    }

}
