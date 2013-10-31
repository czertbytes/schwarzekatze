package de.czertbytes.schwarzekatze.core.infrastructure.impl;

import de.czertbytes.schwarzekatze.core.infrastructure.PetRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PetRepositoryCustomImpl implements PetRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
