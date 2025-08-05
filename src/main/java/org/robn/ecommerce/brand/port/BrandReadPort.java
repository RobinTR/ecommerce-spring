package org.robn.ecommerce.brand.port;

import org.robn.ecommerce.brand.model.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandReadPort {

    List<Brand> findAll();

    Optional<Brand> findById(Long id);

}
