package de.czertbytes.schwarzekatze.api.service.impl;

import de.czertbytes.schwarzekatze.api.converter.domain.ShelterDomainConverter;
import de.czertbytes.schwarzekatze.api.converter.domain.UserDomainConverter;
import de.czertbytes.schwarzekatze.api.domain.Shelter;
import de.czertbytes.schwarzekatze.api.domain.Shelters;
import de.czertbytes.schwarzekatze.api.domain.User;
import de.czertbytes.schwarzekatze.api.domain.Users;
import de.czertbytes.schwarzekatze.api.domain.parameters.ShelterSearchParameters;
import de.czertbytes.schwarzekatze.api.service.SheltersResourceService;
import de.czertbytes.schwarzekatze.core.infrastructure.ShelterRepository;
import de.czertbytes.schwarzekatze.core.infrastructure.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@Service
public class ShelterResourceServiceImpl implements SheltersResourceService {

    @Autowired
    ShelterRepository shelterRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ShelterDomainConverter shelterDomainConverter;

    @Autowired
    UserDomainConverter userDomainConverter;

    @Override
    public Shelters find(ShelterSearchParameters shelterSearchParameters, Pageable pageable) {
        return new Shelters(shelterDomainConverter.toApiShelters(shelterRepository.findAll(pageable).getContent()));
    }

    @Override
    public Long create(Shelter shelter) {
        if (shelterRepository.findByName(shelter.getName()) != null) {
            throw new EntityExistsException();
        }

        return shelterRepository.save(shelterDomainConverter.toCoreShelter(shelter)).getId();
    }

    @Override
    public Shelter find(Long id) {
        de.czertbytes.schwarzekatze.core.domain.shelter.Shelter coreShelter = shelterRepository.findOne(id);
        if (coreShelter == null) {
            throw new EntityNotFoundException();
        }

        return shelterDomainConverter.toApiShelter(coreShelter);
    }

    @Override
    public void update(Long id, Shelter shelter) {
        if (shelterRepository.exists(id)) {
            shelterRepository.save(shelterDomainConverter.toCoreShelter(shelter));
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public void delete(Long id) {
        shelterRepository.delete(id);
    }

    @Override
    public Users findUsers(final Long shelterId, final Pageable pageable) {
        return new Users(userDomainConverter.toApiUsers(userRepository.findByShelter(shelterRepository.findOne(shelterId), pageable).getContent()));
    }

    @Override
    public Long createUser(final Long shelterId, final User user) {
        if (shelterRepository.exists(shelterId)) {
            final de.czertbytes.schwarzekatze.core.domain.shelter.Shelter coreShelter = shelterRepository.findOne(shelterId);
            final de.czertbytes.schwarzekatze.core.domain.user.User coreUser = userRepository.findOne(user.getId());

            coreUser.setShelter(coreShelter);

            userRepository.save(coreUser);

            return coreUser.getId();
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public void deleteUser(final Long shelterId, final Long userId) {
        if (shelterRepository.exists(shelterId)) {
            final de.czertbytes.schwarzekatze.core.domain.shelter.Shelter coreShelter = shelterRepository.findOne(shelterId);
            final de.czertbytes.schwarzekatze.core.domain.user.User coreUser = userRepository.findOne(userId);

            if (coreUser.getShelter().getId().equals(coreShelter.getId())) {
                coreUser.setShelter(null);
            }

            userRepository.save(coreUser);

            shelterRepository.save(coreShelter);
        } else {
            throw new EntityNotFoundException();
        }
    }
}
