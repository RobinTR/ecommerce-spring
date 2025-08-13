package org.robn.ecommerce.product.util.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to validate a list of image files.
 * The images must meet the specified criteria such as size, file type, and number of images.
 * This annotation is used in conjunction with the {@link ImageListValidator} class
 * to perform the actual validation logic.
 * Defaults are provided for minimum and maximum sizes, maximum file size, and allowed content types.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ImageListValidator.class)
public @interface ImageList {

    String message() default "Invalid image files";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
