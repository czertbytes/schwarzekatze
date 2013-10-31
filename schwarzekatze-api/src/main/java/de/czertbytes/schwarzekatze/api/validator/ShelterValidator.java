package de.czertbytes.schwarzekatze.api.validator;

import de.czertbytes.schwarzekatze.api.domain.Shelter;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ShelterValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Shelter.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        final Shelter shelter = (Shelter) target;

        ValidationUtils.rejectIfEmpty(errors, "name", "shelter.name.empty");
        ValidationUtils.rejectIfEmpty(errors, "description", "shelter.description.empty");
    }
}
