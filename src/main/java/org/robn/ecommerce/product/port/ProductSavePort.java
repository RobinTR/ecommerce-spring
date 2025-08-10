package org.robn.ecommerce.product.port;

import org.robn.ecommerce.product.model.Product;

public interface ProductSavePort {

    Product save(Product product);

}
