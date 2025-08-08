package org.robn.ecommerce.common.util.validation.name;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NameWithNumberValidator extends BaseNameValidator implements ConstraintValidator<NameWithNumber, String> {

    private static final String NAME_WITH_NUMBER_REGEX = "^(?!.*[ ,.'-]{2})[a-zA-ZÇçĞğİıÖöŞşÜü0-9]+(?:[ ,.'-](?![ ,.'-])[a-zA-ZÇçĞğİıÖöŞşÜü0-9]+)*$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (isEmpty(value)) {
            return true;
        }

        if (hasInvalidSpaces(value, context)) {
            return false;
        }

        return value.matches(NAME_WITH_NUMBER_REGEX);
    }

}
