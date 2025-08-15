package org.robn.ecommerce.common.util.validation.name;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to validate addresses using {@link AddressValidator}.
 */
@Target(value = ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AddressValidator.class)
public @interface ValidAddress {

    String message() default "must be a valid address (letters, numbers, space, comma, period, apostrophe, hyphen, colon allowed)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
