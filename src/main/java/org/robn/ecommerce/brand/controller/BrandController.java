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
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("permitAll()")
    public EcoBaseResponse<List<BrandListResponse>> findAll() {
        final List<Brand> brands = brandService.findAll();
        final List<BrandListResponse> brandsResponse = brandDomainToListResponseMapper.map(brands);

        return EcoBaseResponse.successOf(brandsResponse);
    }

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public EcoBaseResponse<BrandResponse> findById(@PathVariable final Long id) {
        final Brand brand = brandService.findById(id);
        final BrandResponse brandResponse = brandDomainToResponseMapper.map(brand);

        return EcoBaseResponse.successOf(brandResponse);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public EcoBaseResponse<Void> create(@RequestBody @Valid final BrandCreateRequest brandCreateRequest) {
        brandService.create(brandCreateRequest);

        return EcoBaseResponse.success();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public EcoBaseResponse<Void> update(@PathVariable final Long id, @RequestBody @Valid final BrandUpdateRequest brandUpdateRequest) {
        brandService.update(id, brandUpdateRequest);

        return EcoBaseResponse.success();
    }

}
