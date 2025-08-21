package org.robn.ecommerce.cart.exception;

import org.robn.ecommerce.cart.model.enums.CartStatus;
import org.robn.ecommerce.common.exception.EcoNotFoundException;

import java.io.Serial;
import java.util.UUID;

/**
 * Exception thrown when a guest cart with a specific session ID and cart status is not found.
 * This exception extends {@link EcoNotFoundException} to indicate a specific type of not found error.
 */
public class GuestCartBySessionIdAndCartStatusNotFoundException extends EcoNotFoundException {

    @Serial
    private static final long serialVersionUID = -478317318901002959L;

    /**
     * Constructs a new GuestCartBySessionIdAndCartStatusNotFoundException with the specified session ID and cart status.
     *
     * @param sessionId  the session ID of the guest cart that was not found
     * @param cartStatus the cart status of the guest cart that was not found
     */
    private GuestCartBySessionIdAndCartStatusNotFoundException(final UUID sessionId, final CartStatus cartStatus) {
        super(String.format("Guest cart with session ID '%s' and cart status '%s' not found", sessionId, cartStatus));
    }

    /**
     * Factory method to create a new instance of GuestCartBySessionIdAndCartStatusNotFoundException.
     *
     * @param sessionId  the session ID of the guest cart that was not found
     * @param cartStatus the cart status of the guest cart that was not found
     * @return a new instance of GuestCartBySessionIdAndCartStatusNotFoundException
     */
    public static GuestCartBySessionIdAndCartStatusNotFoundException of(final UUID sessionId, final CartStatus cartStatus) {
        return new GuestCartBySessionIdAndCartStatusNotFoundException(sessionId, cartStatus);
    }

}
