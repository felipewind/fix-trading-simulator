package com.helesto.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
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

    public void persistOrder(OrderEntity order) {
        em.persist(order);
    }



}
