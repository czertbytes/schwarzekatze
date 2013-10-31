package de.czertbytes.schwarzekatze.api.service;

import de.czertbytes.schwarzekatze.api.domain.Shelter;
import de.czertbytes.schwarzekatze.api.domain.Shelters;
import de.czertbytes.schwarzekatze.api.domain.User;
import de.czertbytes.schwarzekatze.api.domain.Users;
import de.czertbytes.schwarzekatze.api.domain.parameters.ShelterSearchParameters;
import org.springframework.data.domain.Pageable;

public interface SheltersResourceService {

    Shelter find(Long id);

    Shelters find(ShelterSearchParameters shelterSearchParameters, Pageable pageable);

    Long create(Shelter shelter);

    void update(Long id, Shelter shelter);

    void delete(Long id);

    Users findUsers(Long shelterId, Pageable pageable);

    Long createUser(Long shelterId, User user);

    void deleteUser(Long shelterId, Long userId);
}
