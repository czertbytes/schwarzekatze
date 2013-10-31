package de.czertbytes.schwarzekatze.api.service;

import de.czertbytes.schwarzekatze.api.domain.Pet;
import de.czertbytes.schwarzekatze.api.domain.Pets;
import de.czertbytes.schwarzekatze.api.domain.parameters.PetSearchParameters;
import org.springframework.data.domain.Pageable;

public interface PetsResourceService {

    Pet find(Long id);

    Pets findAll(PetSearchParameters petSearchParameters, Pageable pageable);
}
