package org.robn.ecommerce.cart.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.cart.model.GuestCart;
import org.robn.ecommerce.cart.model.enums.CartStatus;
import org.robn.ecommerce.cart.port.GuestCartReadPort;
import org.robn.ecommerce.cart.service.GuestCartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GuestCartServiceImpl implements GuestCartService {

    private final GuestCartReadPort guestCartReadPort;

    @Override
    public List<GuestCart> findAllByGuestId(final UUID guestId) {
        return guestCartReadPort.findAllByGuestId(guestId);
    }

    @Override
    public List<GuestCart> findAllByGuestIdAndCartStatus(final UUID guestId, final CartStatus cartStatus) {
        return guestCartReadPort.findAllByGuestIdAndCartStatus(guestId, cartStatus);
    }

}
