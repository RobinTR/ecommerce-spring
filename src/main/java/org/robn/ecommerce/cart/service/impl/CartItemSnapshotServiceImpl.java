package org.robn.ecommerce.cart.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.cart.model.CartItem;
import org.robn.ecommerce.cart.model.CartItemSnapshot;
import org.robn.ecommerce.cart.model.CartSnapshot;
import org.robn.ecommerce.cart.model.enums.CartItemStatus;
import org.robn.ecommerce.cart.model.mapper.CartItemToCartItemSnapshotMapper;
import org.robn.ecommerce.cart.port.CartItemSnapshotSavePort;
import org.robn.ecommerce.cart.service.CartItemService;
import org.robn.ecommerce.cart.service.CartItemSnapshotService;
import org.robn.ecommerce.cart.service.CartSnapshotService;
import org.robn.ecommerce.product.model.Product;
import org.robn.ecommerce.product.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartItemSnapshotServiceImpl implements CartItemSnapshotService {

    private final CartSnapshotService cartSnapshotService;
    private final CartItemService cartItemService;
    private final ProductService productService;
    private final CartItemSnapshotSavePort cartItemSnapshotSavePort;
    private final CartItemToCartItemSnapshotMapper cartItemToCartItemSnapshotMapper;

    @Override
    @Transactional
    public List<CartItemSnapshot> createCartItemSnapshotsFromCartSnapshotId(final UUID cartSnapshotId) {
        final CartSnapshot cartSnapshot = cartSnapshotService.findById(cartSnapshotId);
        final List<CartItem> cartItems = getActiveCartItemsByCartId(cartSnapshot.getCartId());
        final List<CartItemSnapshot> cartItemSnapshots = createCartItemSnapshots(cartItems, cartSnapshotId);

        return cartItemSnapshotSavePort.saveAll(cartItemSnapshots);
    }

    private List<CartItem> getActiveCartItemsByCartId(final UUID cartId) {
        return cartItemService.findAllByCartIdAndStatus(cartId, CartItemStatus.ACTIVE);
    }

    private List<CartItemSnapshot> createCartItemSnapshots(final List<CartItem> cartItems, final UUID cartSnapshotId) {
        final List<Long> productIds = cartItems.stream().map(CartItem::getProductId).toList();
        final Map<Long, Product> productMap = productService.findAllByIds(productIds).stream().collect(Collectors.toMap(Product::getId, product -> product));

        return cartItems.stream().map(cartItem -> createCartItemSnapshot(cartItem, productMap.get(cartItem.getProductId()) ,cartSnapshotId)).toList();
    }

    private CartItemSnapshot createCartItemSnapshot(final CartItem cartItem, final Product product, final UUID cartSnapshotId) {
        final CartItemSnapshot cartItemSnapshot = cartItemToCartItemSnapshotMapper.map(cartItem);
        setCartItemSnapshot(cartItemSnapshot, cartItem, product, cartSnapshotId);

        return cartItemSnapshot;
    }

    private void setCartItemSnapshot(final CartItemSnapshot cartItemSnapshot, final CartItem cartItem, final Product product, final UUID cartSnapshotId) {
        cartItemSnapshot.setCartItemId(cartItem.getId());
        cartItemSnapshot.setCartSnapshotId(cartSnapshotId);
        cartItemSnapshot.setProductSellerId(product.getSellerId());
        cartItemSnapshot.setProductBrandId(product.getBrandId());
        cartItemSnapshot.setProductName(product.getName());
        cartItemSnapshot.setProductDescription(product.getDescription());
        cartItemSnapshot.setProductPrice(product.getPrice());
    }

}
