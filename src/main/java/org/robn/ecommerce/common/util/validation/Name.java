package org.robn.ecommerce.common.util.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation for validating names.
 * This annotation can be used to ensure that a string meets specific criteria for a name.
 */
@Target(value = ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NameValidator.class)
public @interface Name {

    /**
     * Default error message when name validation fails.
     *
     * @return the default error message
     */
    String message() default "must be valid";

    /**
     * Groups for the constraint.
     *
     * @return the groups
     */
    Class<?>[] groups() default {};

    /**
     * Payload for the constraint.
     *
     * @return the payload
     */
    Class<? extends Payload>[] payload() default {};

}
