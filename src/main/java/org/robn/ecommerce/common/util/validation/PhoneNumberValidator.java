package org.robn.ecommerce.common.util.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (!StringUtils.hasText(value)) {
            return true;
        }

        if (!value.startsWith("05")) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("phone number must start with 05")
                    .addConstraintViolation();
            return false;
        }

        if (value.length() != 11) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("phone number length must be 11")
                    .addConstraintViolation();
            return false;
        }

        if (!value.chars().allMatch(Character::isDigit)) {
            return false;
        }

        return true;

    }

}
