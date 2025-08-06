package org.robn.ecommerce.common.util.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

class NameValidator implements ConstraintValidator<Name, String> {

    /**
     * Regular expression to validate names.
     * This regex allows names with letters (including Turkish characters), spaces, commas, periods,
     * apostrophes, and hyphens, but does not allow consecutive spaces or special characters.
     * It ensures that names start with a letter and can include spaces or punctuation between words
     * without leading or trailing spaces.
     */
    private static final String NAME_REGEX = "^(?!.*[ ,.'-]{2})[a-zA-ZÇçĞğİıÖöŞşÜü]+(?:[ ,.'-](?![ ,.'-])[a-zA-ZÇçĞğİıÖöŞşÜü]+)*$";

    /**
     * Regular expression for validating names.
     * This regex allows names to contain letters (including Turkish characters),
     * spaces, commas, periods, apostrophes, and hyphens.
     * It does not allow consecutive spaces or special characters.
     * It ensures that names start with a letter and can include spaces or punctuation
     * between words without leading or trailing spaces.
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (!StringUtils.hasText(value)) {
            return true;
        }

        if (value.startsWith(" ") || value.endsWith(" ")) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "Name must not start or end with a space"
            ).addConstraintViolation();

            return false;
        }

        return value.matches(NAME_REGEX);
    }

}
