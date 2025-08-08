package org.robn.ecommerce.common.util.validation.name;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to validate names using {@link NameValidator}.
 */
@Target(value = ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NameValidator.class)
public @interface Name {

    String message() default "Name must contain only letters and valid special characters, without leading/trailing spaces or consecutive spaces";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
