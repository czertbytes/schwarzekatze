package de.czertbytes.schwarzekatze.core.infrastructure.impl;

import de.czertbytes.schwarzekatze.core.infrastructure.ShelterRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ShelterRepositoryCustomImpl implements ShelterRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
