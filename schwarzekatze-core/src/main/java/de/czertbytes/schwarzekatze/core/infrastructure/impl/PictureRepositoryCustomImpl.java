package de.czertbytes.schwarzekatze.core.infrastructure.impl;

import de.czertbytes.schwarzekatze.core.infrastructure.PictureRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PictureRepositoryCustomImpl implements PictureRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
