package de.czertbytes.schwarzekatze.api.service.impl;

import ch.hsr.geohash.GeoHash;
import com.google.common.collect.Lists;
import de.czertbytes.schwarzekatze.api.converter.domain.ActionDomainConverter;
import de.czertbytes.schwarzekatze.api.converter.domain.PetDomainConverter;
import de.czertbytes.schwarzekatze.api.converter.domain.UserDomainConverter;
import de.czertbytes.schwarzekatze.api.domain.Pet;
import de.czertbytes.schwarzekatze.api.domain.Pets;
import de.czertbytes.schwarzekatze.api.domain.parameters.PetSearchParameters;
import de.czertbytes.schwarzekatze.api.service.PetsResourceService;
import de.czertbytes.schwarzekatze.core.domain.State;
import de.czertbytes.schwarzekatze.core.domain.action.Action;
import de.czertbytes.schwarzekatze.core.domain.action.Type;
import de.czertbytes.schwarzekatze.core.domain.pet.Animal;
import de.czertbytes.schwarzekatze.core.domain.pet.Gender;
import de.czertbytes.schwarzekatze.core.infrastructure.ActionRepository;
import de.czertbytes.schwarzekatze.core.infrastructure.PetRepository;
import de.czertbytes.schwarzekatze.core.infrastructure.PictureRepository;
import de.czertbytes.schwarzekatze.core.infrastructure.UserRepository;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class PetsResourceServiceImpl implements PetsResourceService {

    public static final Logger log = LoggerFactory.getLogger(PetsResourceServiceImpl.class);

    @Autowired
    PetRepository petRepository;

    @Autowired
    ActionRepository actionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PictureRepository pictureRepository;

    @Autowired
    PetDomainConverter petDomainConverter;

    @Autowired
    ActionDomainConverter actionDomainConverter;

    @Autowired
    UserDomainConverter userDomainConverter;

    @Override
    public Pets findAll(PetSearchParameters petSearchParameters, Pageable pageable) {
        List<Type> types = petSearchParameters.getType();
        List<Animal> animals = petSearchParameters.getAnimal();

        List<String> locations = Lists.newArrayList();
        final GeoHash locationGeoHash = GeoHash.fromGeohashString(petSearchParameters.getLocation().substring(0,5));
        if (locationGeoHash != null) {
            locations.add(locationGeoHash.toBase32());

            for (GeoHash geoHashTmp : locationGeoHash.getAdjacent()) {
                locations.add(geoHashTmp.toBase32());
            }
        }


        List<State> states = petSearchParameters.getState();
        if (states == null || states.isEmpty()) {
            states = Lists.newArrayList(State.ACTIVE);
        }

        DateTime dateFrom = petSearchParameters.getFrom();
        DateTime dateTo = petSearchParameters.getTo();
        //  TODO: add datetime validation

        List<Gender> genders = petSearchParameters.getGender();

        if (genders == null || genders.isEmpty()) {
            return new Pets(petDomainConverter.toApiPets(petRepository.findAll(types, states, dateFrom, dateTo, animals, pageable).getContent()));
        } else {
            return new Pets(petDomainConverter.toApiPets(petRepository.findAll(types, states, dateFrom, dateTo, animals, genders, pageable).getContent()));
        }
    }

    @Override
    public Pet find(Long petId) {
        final de.czertbytes.schwarzekatze.core.domain.pet.Pet corePet = petRepository.findOne(petId);
        if (corePet == null) {
            throw new EntityNotFoundException();
        }

        final List<Action> coreActions = actionRepository.findByPet(corePet);

        final Pet apiPet = petDomainConverter.toApiPet(corePet);
        apiPet.setActions(actionDomainConverter.toApiActions(coreActions));
        apiPet.setUser(userDomainConverter.toApiUser(coreActions.get(0).getUser()));

        return apiPet;
    }


}
