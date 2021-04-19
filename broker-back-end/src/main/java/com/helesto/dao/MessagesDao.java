package com.helesto.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.helesto.model.MessagesEntity;

@ApplicationScoped
public class MessagesDao {

    @PersistenceContext
    EntityManager em;

    public List<MessagesEntity> listMessages(String sendercompid) {

        TypedQuery<MessagesEntity> query = em.createNamedQuery("Messages.findAllBySenderCompID", MessagesEntity.class);

        query.setParameter("sendercompid", sendercompid);

        return query.getResultList();

    }

}
