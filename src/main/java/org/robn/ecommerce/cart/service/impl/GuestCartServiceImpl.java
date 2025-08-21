package org.robn.ecommerce.cart.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.cart.exception.GuestCartBySessionIdAndCartStatusNotFoundException;
import org.robn.ecommerce.cart.model.GuestCart;
import org.robn.ecommerce.cart.model.enums.CartStatus;
import org.robn.ecommerce.cart.port.GuestCartReadPort;
import org.robn.ecommerce.cart.port.GuestCartSavePort;
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
    private final GuestCartSavePort guestCartSavePort;

    @Override
    public List<GuestCart> findAllBySessionId(final UUID sessionId) {
        return guestCartReadPort.findAllBySessionId(sessionId);
    }

    @Override
    public GuestCart findBySessionIdAndCartStatus(final UUID sessionId, final CartStatus cartStatus) {
        return guestCartReadPort.findBySessionIdAndCartStatus(sessionId, cartStatus).orElseThrow(() -> GuestCartBySessionIdAndCartStatusNotFoundException.of(sessionId, cartStatus));
    }

}
