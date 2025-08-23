package org.robn.ecommerce.common.util.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

/**
 * Validates that a password meets the following criteria:
 * - At least one digit
 * - At least one lowercase letter (Unicode aware)
 * - At least one uppercase letter (Unicode aware)
 * - At least one special character
 * - No whitespace characters
 * - Length between 8 and 128 characters
 */
public class PasswordValidator implements ConstraintValidator<Password, String> {

    private static final Pattern DIGIT_PATTERN = Pattern.compile(".*\\p{N}.*");
    private static final Pattern LOWERCASE_PATTERN = Pattern.compile(".*\\p{Ll}.*");
    private static final Pattern UPPERCASE_PATTERN = Pattern.compile(".*\\p{Lu}.*");
    private static final Pattern SPECIAL_CHAR_PATTERN = Pattern.compile(".*[\\p{P}\\p{S}].*");
    private static final Pattern NO_WHITESPACE_PATTERN = Pattern.compile("^\\S+$");

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (!StringUtils.hasText(password)) {
            return true;
        }

        if (password.length() < 8 || password.length() > 128) {
            return buildViolation(context, "Password must be between 8 and 128 characters long");
        }

        if (!DIGIT_PATTERN.matcher(password).matches()) {
            return buildViolation(context, "At least one digit is required");
        }

        if (!LOWERCASE_PATTERN.matcher(password).matches()) {
            return buildViolation(context, "At least one lowercase letter is required");
        }

        if (!UPPERCASE_PATTERN.matcher(password).matches()) {
            return buildViolation(context, "At least one uppercase letter is required");
        }

        if (!SPECIAL_CHAR_PATTERN.matcher(password).matches()) {
            return buildViolation(context, "At least one special character is required");
        }

        if (!NO_WHITESPACE_PATTERN.matcher(password).matches()) {
            return buildViolation(context, "Password must not contain whitespace characters");
        }

        return true;
    }

    private boolean buildViolation(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();

        return false;
    }

}
