package org.robn.ecommerce.common.util.validation.name;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to validate names that can include numbers using {@link NameWithNumberValidator}.
 */
@Target(value = ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NameWithNumberValidator.class)
public @interface NameWithNumber {

    String message() default "must be valid name with numbers allowed";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
