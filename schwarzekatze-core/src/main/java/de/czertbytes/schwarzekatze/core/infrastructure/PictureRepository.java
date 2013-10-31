package de.czertbytes.schwarzekatze.core.infrastructure;

import de.czertbytes.schwarzekatze.core.domain.picture.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PictureRepository extends JpaRepository<Picture, Long>, JpaSpecificationExecutor<Picture>, PictureRepositoryCustom {
}
