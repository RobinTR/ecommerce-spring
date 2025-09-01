package org.robn.ecommerce.common.util.validation.name;

import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

public abstract class BaseNameValidator {

    protected boolean isEmpty(final String value) {
        return !StringUtils.hasText(value);
    }

    protected boolean hasLeadingOrTrailingSpaces(final String value) {
        return value.startsWith(" ") || value.endsWith(" ");
    }

    protected boolean hasInvalidSpaces(final String value, final ConstraintValidatorContext context) {
        if (hasLeadingOrTrailingSpaces(value)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "Name must not start or end with a space"
            ).addConstraintViolation();

            return true;
        }

        return false;
    }

}