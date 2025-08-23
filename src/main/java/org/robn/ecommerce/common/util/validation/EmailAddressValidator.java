package org.robn.ecommerce.common.util.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

/**
 * Validator for email addresses.
 * <p>
 * This validator checks if the provided email address is valid according to the following rules:
 * <ul>
 *     <li>The email must be between 6 and 254 characters in length.</li>
 *     <li>The local part (before the '@') must start with an alphanumeric character and can contain
 *         alphanumeric characters, dots (.), underscores (_), percent signs (%), plus signs (+), and hyphens (-).
 *         It cannot have consecutive dots.</li>
 *     <li>The domain part (after the '@') must consist of labels separated by dots. Each label must start and end
 *         with an alphanumeric character and can contain hyphens (-) in between. The top-level domain must be at
 *         least two alphabetic characters long.</li>
 * </ul>
 * <p>
 * If the email is null or empty, it is considered valid.
 */
public class EmailAddressValidator implements ConstraintValidator<EmailAddress, String> {

    private static final Pattern EMAIL_REGEX = Pattern.compile(
            "^(?!.*\\.{2})" +
                    "[\\p{Alnum}][\\p{Alnum}._%+\\-]*" +
                    "@" +
                    "(?!-)(?:[\\p{Alnum}]+(?<!-)\\.)+" +
                    "[\\p{Alpha}]{2,}$",
            Pattern.UNICODE_CHARACTER_CLASS
    );

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {

        if (!StringUtils.hasText(email)) {
            return true;
        }

        if (email.length() < 6 || email.length() > 254) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Size must be between 6 and 254 characters")
                    .addConstraintViolation();

            return false;
        }

        return EMAIL_REGEX.matcher(email).matches();
    }

}
