package com.helesto.dao;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.helesto.model.SessionsEntity;

@ApplicationScoped
public class SessionsDao {

    @Inject
    EntityManager em;

    public Optional<SessionsEntity> readSession(String sendercompid) {

        Query query = em.createNamedQuery("Sessions.findBySenderCompID");

        query.setParameter("sendercompid", sendercompid);

        try {
            return Optional.of((SessionsEntity) query.getSingleResult());

        } catch (NoResultException nre) {
            return Optional.empty();

        }

    }

}
