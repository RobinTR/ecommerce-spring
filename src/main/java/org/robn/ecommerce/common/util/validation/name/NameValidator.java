package org.robn.ecommerce.common.util.validation.name;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NameValidator extends BaseNameValidator implements ConstraintValidator<Name, String> {

    private static final String NAME_REGEX = "^(?!.*[ ,.'-]{2})[a-zA-ZÇçĞğİıÖöŞşÜü]+(?:[ ,.'-](?![ ,.'-])[a-zA-ZÇçĞğİıÖöŞşÜü]+)*$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (isEmpty(value)) {
            return true;
        }

        if (hasInvalidSpaces(value, context)) {
            return false;
        }

        return value.matches(NAME_REGEX);
    }

}
