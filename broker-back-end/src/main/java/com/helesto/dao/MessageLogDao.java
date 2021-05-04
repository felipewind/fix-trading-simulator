package com.helesto.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.helesto.model.MessageLogIncomingEntity;
import com.helesto.model.MessageLogOutgoingEntity;

@ApplicationScoped
public class MessageLogDao {

    @Inject
    EntityManager em;

    public List<MessageLogIncomingEntity> listMessageLogIncoming(String sendercompid) {

        TypedQuery<MessageLogIncomingEntity> query = em.createNamedQuery("MessageLogIncoming.findAllBySenderCompID",
                MessageLogIncomingEntity.class);

        query.setParameter("sendercompid", sendercompid);

        return query.getResultList();

    }

    public List<MessageLogOutgoingEntity> listMessageLogOutgoing(String sendercompid) {

        TypedQuery<MessageLogOutgoingEntity> query = em.createNamedQuery("MessageLogOutgoing.findAllBySenderCompID",
                MessageLogOutgoingEntity.class);

        query.setParameter("sendercompid", sendercompid);

        return query.getResultList();

    }

}
