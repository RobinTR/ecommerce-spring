package org.robn.ecommerce.product.exception;

import org.robn.ecommerce.common.exception.EcoNotFoundException;

public class ProductImageNotFoundException extends EcoNotFoundException {
    public ProductImageNotFoundException(Long id) {
        super(String.format("No images found for product with id: %d ", id));
    }
}
