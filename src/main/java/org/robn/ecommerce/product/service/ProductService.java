package org.robn.ecommerce.product.service;

import org.robn.ecommerce.product.model.Product;
import org.robn.ecommerce.product.model.request.ProductCreateRequest;
import org.robn.ecommerce.product.model.request.ProductUpdateRequest;

import java.util.List;

public interface ProductService {

    List<Product> findAll();
    Product findById(Long id);
    void create(ProductCreateRequest productCreateRequest);
    void update(Long id, ProductUpdateRequest productUpdateRequest);

}
