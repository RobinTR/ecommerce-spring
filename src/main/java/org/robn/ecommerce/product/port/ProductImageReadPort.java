package org.robn.ecommerce.product.port;

import org.robn.ecommerce.product.model.ProductImage;

import java.util.List;

public interface ProductImageReadPort {

    List<ProductImage> findAll();

    List<ProductImage> findAllByProductId(Long productId);

}
