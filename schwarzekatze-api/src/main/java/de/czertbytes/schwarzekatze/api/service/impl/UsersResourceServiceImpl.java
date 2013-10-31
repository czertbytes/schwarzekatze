package de.czertbytes.schwarzekatze.api.service.impl;

import de.czertbytes.schwarzekatze.api.converter.domain.ActionDomainConverter;
import de.czertbytes.schwarzekatze.api.converter.domain.PetDomainConverter;
import de.czertbytes.schwarzekatze.api.converter.domain.UserDomainConverter;
import de.czertbytes.schwarzekatze.api.domain.Pet;
import de.czertbytes.schwarzekatze.api.domain.Pets;
import de.czertbytes.schwarzekatze.api.domain.User;
import de.czertbytes.schwarzekatze.api.domain.Users;
import de.czertbytes.schwarzekatze.api.domain.parameters.UserSearchParameters;
import de.czertbytes.schwarzekatze.api.service.UsersResourceService;
import de.czertbytes.schwarzekatze.core.infrastructure.ActionRepository;
import de.czertbytes.schwarzekatze.core.infrastructure.PetRepository;
import de.czertbytes.schwarzekatze.core.infrastructure.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@Service
public class UsersResourceServiceImpl implements UsersResourceService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ActionRepository actionRepository;

    @Autowired
    PetRepository petRepository;

    @Autowired
    UserDomainConverter userDomainConverter;

    @Autowired
    PetDomainConverter petDomainConverter;

    @Autowired
    ActionDomainConverter actionDomainConverter;

    @Override
    public Users find(UserSearchParameters userSearchParameters, Pageable pageable) {
        return new Users(userDomainConverter.toApiUsers(userRepository.findAll(pageable).getContent()));
    }

    @Override
    public Long create(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new EntityExistsException();
        }

        return userRepository.save(userDomainConverter.toCoreUser(user)).getId();
    }

    @Override
    public User find(Long id) {
        de.czertbytes.schwarzekatze.core.domain.user.User coreUser = userRepository.findOne(id);
        if (coreUser == null) {
            throw new EntityNotFoundException();
        }

        return userDomainConverter.toApiUser(coreUser);
    }

    @Override
    public void update(Long id, User user) {
        if (userRepository.exists(id)) {
            userRepository.save(userDomainConverter.toCoreUser(user));
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public void delete(final Long id) {
        userRepository.delete(id);
    }

    @Override
    public Pets findPets(final Long userId, Pageable pageable) {
        return new Pets(petDomainConverter.toApiPets(petRepository.findByUser(userId, pageable).getContent()));
    }

    @Override
    public Long createPet(final Long userId, final Pet pet) {
        final de.czertbytes.schwarzekatze.core.domain.user.User coreUser = userRepository.findOne(userId);
        if (coreUser == null) {
            throw new EntityNotFoundException();
        }

        final de.czertbytes.schwarzekatze.core.domain.pet.Pet corePet = petRepository.save(petDomainConverter.toCorePet(pet));

        actionRepository.save(actionDomainConverter.toCoreAction(pet.getAction(), corePet, coreUser));

        return corePet.getId();
    }

    @Override
    public void updatePet(Long userId, Pet pet) {
        if (petRepository.exists(pet.getId())) {
            petRepository.save(petDomainConverter.toCorePet(pet));
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public void deletePet(Long userId, Long petId) {
        petRepository.delete(petId);
    }
}
