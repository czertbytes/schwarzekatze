package de.czertbytes.schwarzekatze.core.infrastructure;

import de.czertbytes.schwarzekatze.core.domain.shelter.Shelter;
import de.czertbytes.schwarzekatze.core.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>, UserRepositoryCustom {

    User findByUsername(String username);

    Page<User> findByShelter(Shelter shelter, Pageable pageable);
}
