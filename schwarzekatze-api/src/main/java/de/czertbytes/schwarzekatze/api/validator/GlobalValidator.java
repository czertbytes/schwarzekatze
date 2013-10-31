package de.czertbytes.schwarzekatze.api.validator;

import com.google.common.collect.Maps;
import de.czertbytes.schwarzekatze.api.domain.Pet;
import de.czertbytes.schwarzekatze.api.domain.Shelter;
import de.czertbytes.schwarzekatze.api.domain.User;
import de.czertbytes.schwarzekatze.api.domain.parameters.PetSearchParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Map;

@Component
public class GlobalValidator implements Validator {

    private final Map<Class, Validator> validators;

    @Autowired
    public GlobalValidator(UserValidator userValidator,
                           ShelterValidator shelterValidator,
                           PetValidator petValidator,
                           PetSearchParametersValidator petSearchParametersValidator) {
        this.validators = Maps.newHashMap();
        this.validators.put(User.class, userValidator);
        this.validators.put(Shelter.class, shelterValidator);
        this.validators.put(Pet.class, petValidator);
        this.validators.put(PetSearchParameters.class, petSearchParametersValidator);
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public void validate(Object target, Errors errors) {
        validators.get(target.getClass()).validate(target, errors);
    }
}
