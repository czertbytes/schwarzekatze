package de.czertbytes.schwarzekatze.api.validator;

import de.czertbytes.schwarzekatze.api.domain.Pet;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PetValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Pet.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        final Pet pet = (Pet) target;

        ValidationUtils.rejectIfEmpty(errors, "animal", "pet.animal.empty");
        ValidationUtils.rejectIfEmpty(errors, "action", "pet.action.empty");
        ValidationUtils.rejectIfEmpty(errors, "action.dateTime", "pet.action.dateTime.empty");
        ValidationUtils.rejectIfEmpty(errors, "action.type", "pet.action.type.empty");
        ValidationUtils.rejectIfEmpty(errors, "action.location", "pet.action.location.empty");
    }
}
