package org.robn.ecommerce.productcategory.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.auth.exception.EcoAccessDeniedException;
import org.robn.ecommerce.auth.port.SecurityReadPort;
import org.robn.ecommerce.common.service.BaseSecurityService;
import org.robn.ecommerce.product.model.Product;
import org.robn.ecommerce.product.service.ProductService;
import org.robn.ecommerce.productcategory.service.ProductCategorySecurityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductCategorySecurityServiceImpl implements ProductCategorySecurityService {

    private final BaseSecurityService baseSecurityService;
    private final ProductService productService;
    private final SecurityReadPort securityReadPort;

    @Override
    public void checkAccessByProductId(final Long productId) {
        if (baseSecurityService.isAdmin()) {
            return;
        }

        final Product product = productService.findById(productId);

        if (!product.isOwnedBy(securityReadPort.getCurrentUserId())) {
            throw EcoAccessDeniedException.of();
        }
    }

}
