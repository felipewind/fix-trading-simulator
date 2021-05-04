package com.helesto.dao;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.helesto.model.OrderEntity;

@ApplicationScoped
public class OrderDao {

    @Inject
    EntityManager em;

    public List<OrderEntity> listOrders() {

        TypedQuery<OrderEntity> query = em.createNamedQuery("Orders.findAll", OrderEntity.class);

        return query.getResultList();

    }

    public Optional<OrderEntity> readByOrderID(int orderID) {

        Query query = em.createNamedQuery("Orders.findByOrderID");

        query.setParameter("orderID", orderID);

        try {
            return Optional.of((OrderEntity) query.getSingleResult());
        } catch (NoResultException nre) {
            return Optional.empty();
        }

    }

    public Optional<OrderEntity> readByOrigClOrdID(int origClOrdID) {

        Query query = em.createNamedQuery("Orders.findByOrigClOrdID");

        query.setParameter("origClOrdID", origClOrdID);

        try {
            return Optional.of((OrderEntity) query.getSingleResult());
        } catch (NoResultException nre) {
            return Optional.empty();
        }

    }

    public void persistOrder(OrderEntity order) {
        em.persist(order);
    }

    public void updateOrder(OrderEntity order) {
        em.merge(order);
    }

}
