package org.robn.ecommerce.productcategory.service;

public interface ProductCategorySecurityService {

    void checkAccessByProductId(Long productId);

}
