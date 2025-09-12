package org.robn.ecommerce.common.util.validation.name;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validates that the annotated string:
 * - Does not start or end with spaces
 * - Does not contain consecutive spaces
 * - Only contains alphabetic, numeric, and allowed special characters (space, comma, period, apostrophe, hyphen, colon)
 * - Supports Turkish characters (Çç, Ğğ, İı, Öö, Şş, Üü)
 * - Allows numbers (0-9) and colon (:) within the address
 */
public class AddressValidator extends BaseNameValidator implements ConstraintValidator<ValidAddress, String> {

    private static final String ADDRESS_REGEX = "^[a-zA-ZÇçĞğİıÖöŞşÜü0-9,.'’: -]+$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (isEmpty(value)) {
            return true;
        }

        // Length check is performed here to prevent processing very long strings
        // for performance and security reasons, even if @Size is also used elsewhere.
        if (value.length() > 1000) {
            return false;
        }

        if (hasInvalidSpaces(value, context)) {
            return false;
        }

        return value.matches(ADDRESS_REGEX);

    }
}
