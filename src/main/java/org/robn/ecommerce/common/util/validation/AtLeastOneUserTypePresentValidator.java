package org.robn.ecommerce.common.util.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.robn.ecommerce.cart.model.request.AddToCartRequest;

/**
 * Validator for the {@link AtLeastOneUserTypePresent} annotation.
 * This class checks if at least one of the user types (customerId or sessionId) is present in the AddToCartRequest.
 */
public class AtLeastOneUserTypePresentValidator implements ConstraintValidator<AtLeastOneUserTypePresent, AddToCartRequest> {

    @Override
    public boolean isValid(AddToCartRequest value, ConstraintValidatorContext context) {
        return value != null && (value.getCustomerId() != null || value.getSessionId() != null);
    }

}
