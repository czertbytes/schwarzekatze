package de.czertbytes.schwarzekatze.core.infrastructure.impl;

import de.czertbytes.schwarzekatze.core.infrastructure.ActionRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ActionRepositoryCustomImpl implements ActionRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
