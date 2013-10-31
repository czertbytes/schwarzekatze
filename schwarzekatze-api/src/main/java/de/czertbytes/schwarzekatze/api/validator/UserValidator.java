package de.czertbytes.schwarzekatze.api.validator;

import de.czertbytes.schwarzekatze.api.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        final User user = (User) target;

        ValidationUtils.rejectIfEmpty(errors, "username", "user.username.empty");
        ValidationUtils.rejectIfEmpty(errors, "email", "user.email.empty");
    }
}
