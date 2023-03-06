package com.stabletrader.v2.databaseservices.repositories;

import jakarta.persistence.EntityManager;

@FunctionalInterface
public interface QueryCallback<T> {
  T queryByEntityManager(EntityManager entityManager);
}
