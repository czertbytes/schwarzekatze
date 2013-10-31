package de.czertbytes.schwarzekatze.api.validator;

import de.czertbytes.schwarzekatze.api.domain.parameters.PetSearchParameters;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PetSearchParametersValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return PetSearchParametersValidator.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        final PetSearchParameters petSearchParameters = (PetSearchParameters) target;

        ValidationUtils.rejectIfEmpty(errors, "type", "petsearch.type.empty");
        ValidationUtils.rejectIfEmpty(errors, "animal", "petsearch.animal.empty");
        ValidationUtils.rejectIfEmpty(errors, "location", "petsearch.location.empty");
    }
}
