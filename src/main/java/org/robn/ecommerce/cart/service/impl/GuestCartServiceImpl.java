package org.robn.ecommerce.cart.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.cart.model.Cart;
import org.robn.ecommerce.cart.model.GuestCart;
import org.robn.ecommerce.cart.model.enums.CartStatus;
import org.robn.ecommerce.cart.model.request.AddToCartRequest;
import org.robn.ecommerce.cart.port.GuestCartReadPort;
import org.robn.ecommerce.cart.port.GuestCartSavePort;
import org.robn.ecommerce.cart.service.CartItemService;
import org.robn.ecommerce.cart.service.CartService;
import org.robn.ecommerce.cart.service.GuestCartService;
import org.robn.ecommerce.guest.model.Guest;
import org.robn.ecommerce.guest.service.GuestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GuestCartServiceImpl implements GuestCartService {

    private final GuestCartReadPort guestCartReadPort;
    private final GuestCartSavePort guestCartSavePort;
    private final GuestService guestService;
    private final CartService cartService;
    private final CartItemService cartItemService;

    @Override
    public List<GuestCart> findAllByGuestId(final UUID guestId) {
        return guestCartReadPort.findAllByGuestId(guestId);
    }

    @Override
    public List<GuestCart> findAllByGuestIdAndCartStatus(final UUID guestId, final CartStatus cartStatus) {
        return guestCartReadPort.findAllByGuestIdAndCartStatus(guestId, cartStatus);
    }

    @Override
    @Transactional
    public GuestCart create(final String deviceId, final AddToCartRequest addToCartRequest) {
        final Guest guest = guestService.findByDeviceId(deviceId).orElseGet(() -> guestService.create(deviceId));
        final List<GuestCart> existingCarts = guestCartReadPort.findAllByGuestIdAndCartStatus(guest.getId(), CartStatus.ACTIVE);

        if (!existingCarts.isEmpty()) {
            return updateExistingCart(existingCarts.getFirst(), addToCartRequest);
        }

        return createNewCart(guest, addToCartRequest);
    }

    private GuestCart createNewCart(final Guest guest, final AddToCartRequest request) {
        final GuestCart newGuestCart = GuestCart.builder()
                .guestId(guest.getId())
                .discountAmount(BigDecimal.ZERO)
                .cartStatus(CartStatus.ACTIVE)
                .build();
        final GuestCart savedCart = guestCartSavePort.save(newGuestCart);
        cartItemService.create(savedCart.getId(), request.productId(), request.quantity());
        savedCart.setTotalPrice(cartService.calculateCartTotalPrice(savedCart.getId()));

        return savedCart;
    }

    private GuestCart updateExistingCart(final GuestCart existingCart, final AddToCartRequest request) {
        final Cart cart = cartService.update(existingCart.getId(), request);
        existingCart.setTotalPrice(cart.getTotalPrice());

        return existingCart;
    }

}
