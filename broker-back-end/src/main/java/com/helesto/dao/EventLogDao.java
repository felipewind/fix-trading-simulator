package com.helesto.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.helesto.model.EventLogEntity;

@ApplicationScoped
public class EventLogDao {

    @PersistenceContext
    EntityManager em;

    public List<EventLogEntity> listEventLog(String sendercompid) {

        TypedQuery<EventLogEntity> query = em.createNamedQuery("EventLog.findAllBySenderCompID", EventLogEntity.class);

        query.setParameter("sendercompid", sendercompid);

        return query.getResultList();

    }

}
