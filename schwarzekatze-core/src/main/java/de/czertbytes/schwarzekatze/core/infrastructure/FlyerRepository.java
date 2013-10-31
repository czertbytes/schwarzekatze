package de.czertbytes.schwarzekatze.core.infrastructure;

import de.czertbytes.schwarzekatze.core.domain.flyer.Flyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FlyerRepository extends JpaRepository<Flyer, Long>, JpaSpecificationExecutor<Flyer>, FlyerRepositoryCustom {
}
