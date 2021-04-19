package com.helesto.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.helesto.model.MessageLogIncomingEntity;

@ApplicationScoped
public class MessageLogDao {

    @PersistenceContext
    EntityManager em;

    public List<MessageLogIncomingEntity> listMessageLogIncoming(String sendercompid) {

        TypedQuery<MessageLogIncomingEntity> query = em.createNamedQuery("MessageLogIncoming.findAllBySenderCompID",
                MessageLogIncomingEntity.class);

        query.setParameter("sendercompid", sendercompid);

        return query.getResultList();

    }

}
