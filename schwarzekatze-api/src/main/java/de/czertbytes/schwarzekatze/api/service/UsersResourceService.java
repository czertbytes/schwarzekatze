package de.czertbytes.schwarzekatze.api.service;

import de.czertbytes.schwarzekatze.api.domain.Pet;
import de.czertbytes.schwarzekatze.api.domain.Pets;
import de.czertbytes.schwarzekatze.api.domain.User;
import de.czertbytes.schwarzekatze.api.domain.Users;
import de.czertbytes.schwarzekatze.api.domain.parameters.UserSearchParameters;
import org.springframework.data.domain.Pageable;

public interface UsersResourceService {

    Users find(UserSearchParameters userSearchParameters, Pageable pageable);

    Long create(User user);

    User find(Long id);

    void update(Long id, User user);

    void delete(Long id);

    Pets findPets(Long userId, Pageable pageable);
    
    Long createPet(Long userId, Pet pet);

    void updatePet(Long userId, Pet pet);

    void deletePet(Long userId, Long petId);
}
