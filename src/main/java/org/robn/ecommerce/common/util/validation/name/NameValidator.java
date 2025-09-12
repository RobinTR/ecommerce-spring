package org.robn.ecommerce.common.util.validation.name;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validates that the annotated string:
 * - Does not start or end with spaces
 * - Does not contain consecutive spaces
 * - Only contains alphabetic characters and allowed special characters (space, comma, period, apostrophe, hyphen)
 * - Supports Turkish characters (Çç, Ğğ, İı, Öö, Şş, Üü)
 */
public class NameValidator extends BaseNameValidator implements ConstraintValidator<Name, String> {

    private static final String NAME_REGEX = "^(?!.*[ ,.'-]{2})[a-zA-ZÇçĞğİıÖöŞşÜü]+(?:[ ,.'-](?![ ,.'-])[a-zA-ZÇçĞğİıÖöŞşÜü]+)*$";

    /**
     * Validates the provided name against the defined regex pattern.
     * <p>Some examples of valid names:</p>
     * <ul>
     *     <li>John Doe</li>
     *     <li>Jane,Smith</li>
     *     <li>O'Connor</li>
     *     <li>Anne-Marie</li>
     * </ul>
     *
     * <p>Some examples of invalid names:</p>
     * <ul>
     *     <li>John  Doe (contains consecutive spaces)</li>
     *     <li> John Doe (starts with a space)</li>
     *     <li>John Doe  (ends with a space)</li>
     *     <li>John@Doe (contains invalid character '@')</li>
     *     <li>John*Smith (contains invalid character '*')</li>
     * </ul>
     *
     * @param value   the name to validate
     * @param context the validation context
     * @return true if valid, false otherwise
     */
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

        return value.matches(NAME_REGEX);
    }

}
