package org.robn.ecommerce.cart.exception;

import org.robn.ecommerce.common.exception.EcoNotFoundException;

import java.io.Serial;
import java.util.UUID;

/**
 * Exception thrown when a CartItem with a specific cart ID and product ID is not found.
 */
public class CartItemByIdAndProductNotFoundException extends EcoNotFoundException {

    @Serial
    private static final long serialVersionUID = 6958962940858327143L;

    /**
     * Constructs a new CartItemByIdAndProductNotFoundException with the specified cart ID and product ID.
     *
     * @param cartId    the ID of the cart
     * @param productId the ID of the product
     */
    private CartItemByIdAndProductNotFoundException(final UUID cartId, final Long productId) {
        super(String.format("CartItem with id=%s and productId=%s not found", cartId, productId));
    }

    /**
     * Factory method to create a new instance of CartItemByIdAndProductNotFoundException.
     *
     * @param cartId    the ID of the cart
     * @param productId the ID of the product
     * @return a new instance of CartItemByIdAndProductNotFoundException
     */
    public static CartItemByIdAndProductNotFoundException of(final UUID cartId, final Long productId) {
        return new CartItemByIdAndProductNotFoundException(cartId, productId);
    }

}
