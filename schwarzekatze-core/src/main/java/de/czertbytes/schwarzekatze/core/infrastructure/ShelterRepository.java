package de.czertbytes.schwarzekatze.core.infrastructure;

import de.czertbytes.schwarzekatze.core.domain.shelter.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShelterRepository extends JpaRepository<Shelter, Long>, JpaSpecificationExecutor<Shelter>, ShelterRepositoryCustom {

    Shelter findByName(String name);
}
