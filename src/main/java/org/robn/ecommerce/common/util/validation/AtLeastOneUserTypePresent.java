package org.robn.ecommerce.common.util.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom validation annotation to ensure that at least one of the user types (customerId or sessionId) is present.
 * This annotation can be applied to classes to validate the presence of either a customer ID or a session ID.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AtLeastOneUserTypePresentValidator.class)
public @interface AtLeastOneUserTypePresent {

    String message() default "Either customerId or sessionId must be present";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
