package org.robn.ecommerce.common.util.validation.name;

import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

public abstract class BaseNameValidator {

    protected boolean isEmpty(String value) {
        return !StringUtils.hasText(value);
    }

    protected boolean hasLeadingOrTrailingSpaces(String value) {
        return value.startsWith(" ") || value.endsWith(" ");
    }

    protected boolean hasInvalidSpaces(String value, ConstraintValidatorContext context) {
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