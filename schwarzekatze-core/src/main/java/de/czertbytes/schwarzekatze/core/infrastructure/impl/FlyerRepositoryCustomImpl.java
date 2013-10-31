package de.czertbytes.schwarzekatze.core.infrastructure.impl;

import de.czertbytes.schwarzekatze.core.infrastructure.FlyerRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class FlyerRepositoryCustomImpl implements FlyerRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
