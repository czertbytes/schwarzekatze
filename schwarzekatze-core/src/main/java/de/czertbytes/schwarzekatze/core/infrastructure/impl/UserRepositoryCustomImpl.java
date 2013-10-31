package de.czertbytes.schwarzekatze.core.infrastructure.impl;

import de.czertbytes.schwarzekatze.core.infrastructure.UserRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
