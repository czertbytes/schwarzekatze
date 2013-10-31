package de.czertbytes.schwarzekatze.api.converter.domain;

import com.google.common.collect.Lists;
import de.czertbytes.schwarzekatze.api.domain.Pet;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PetDomainConverter {

    public Pet toApiPet(final de.czertbytes.schwarzekatze.core.domain.pet.Pet corePet) {
        final Pet apiPet = new Pet();

        apiPet.setId(corePet.getId());
        apiPet.setName(corePet.getName());
        apiPet.setAnimal(corePet.getAnimal());
        apiPet.setBreed(corePet.getBreed());
        apiPet.setGender(corePet.getGender());
        apiPet.setAge(corePet.getAge());
        apiPet.setDescription(corePet.getDescription());

        return apiPet;
    }

    public List<Pet> toApiPets(final List<de.czertbytes.schwarzekatze.core.domain.pet.Pet> corePets) {
        final List<Pet> apiPets = Lists.newArrayList();

        for (de.czertbytes.schwarzekatze.core.domain.pet.Pet corePet : corePets) {
            apiPets.add(toApiPet(corePet));
        }

        return apiPets;
    }

    public de.czertbytes.schwarzekatze.core.domain.pet.Pet toCorePet(final Pet apiPet) {
        final de.czertbytes.schwarzekatze.core.domain.pet.Pet corePet = new de.czertbytes.schwarzekatze.core.domain.pet.Pet();

        corePet.setId(apiPet.getId());
        corePet.setName(apiPet.getName());
        corePet.setAnimal(apiPet.getAnimal());
        corePet.setBreed(apiPet.getBreed());
        corePet.setGender(apiPet.getGender());
        corePet.setAge(apiPet.getAge());
        corePet.setDescription(apiPet.getDescription());

        return corePet;
    }
}
