package org.robn.ecommerce.product.port;

import org.robn.ecommerce.product.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductReadPort {

    List<Product> findAll();

    List<Product> findAllByIds(List<Long> ids);

    Optional<Product> findById(Long id);

}
