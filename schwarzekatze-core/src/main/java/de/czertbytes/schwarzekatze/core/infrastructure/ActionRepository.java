package de.czertbytes.schwarzekatze.core.infrastructure;

import de.czertbytes.schwarzekatze.core.domain.action.Action;
import de.czertbytes.schwarzekatze.core.domain.pet.Pet;
import de.czertbytes.schwarzekatze.core.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ActionRepository extends JpaRepository<Action, Long>, JpaSpecificationExecutor<Action>, ActionRepositoryCustom {

    List<Action> findByPet(Pet pet);

    Page<Action> findByUser(User user, Pageable pageable);

}
