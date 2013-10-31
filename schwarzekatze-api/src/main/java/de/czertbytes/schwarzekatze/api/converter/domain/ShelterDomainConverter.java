package de.czertbytes.schwarzekatze.api.converter.domain;

import com.google.common.collect.Lists;
import de.czertbytes.schwarzekatze.api.domain.Shelter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShelterDomainConverter {

    public Shelter toApiShelter(final de.czertbytes.schwarzekatze.core.domain.shelter.Shelter coreShelter) {
        final Shelter apiShelter = new Shelter();

        apiShelter.setId(coreShelter.getId());
        apiShelter.setName(coreShelter.getName());
        apiShelter.setDescription(coreShelter.getDescription());

        return apiShelter;
    }

    public List<Shelter> toApiShelters(final List<de.czertbytes.schwarzekatze.core.domain.shelter.Shelter> coreShelters) {
        final List<Shelter> apiShelters = Lists.newArrayList();

        for (de.czertbytes.schwarzekatze.core.domain.shelter.Shelter coreShelter : coreShelters) {
            apiShelters.add(toApiShelter(coreShelter));
        }

        return apiShelters;
    }

    public de.czertbytes.schwarzekatze.core.domain.shelter.Shelter toCoreShelter(final Shelter apiShelter) {
        final de.czertbytes.schwarzekatze.core.domain.shelter.Shelter coreShelter = new de.czertbytes.schwarzekatze.core.domain.shelter.Shelter();

        coreShelter.setId(apiShelter.getId());
        coreShelter.setName(apiShelter.getName());
        coreShelter.setDescription(apiShelter.getDescription());

        return coreShelter;
    }
}
