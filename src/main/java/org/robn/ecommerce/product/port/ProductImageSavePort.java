package org.robn.ecommerce.product.port;

import org.robn.ecommerce.product.model.ProductImage;

public interface ProductImageSavePort {

    ProductImage save(ProductImage productImage);

}
