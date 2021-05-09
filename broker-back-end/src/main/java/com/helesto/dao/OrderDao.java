package com.helesto.dao;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.helesto.dto.OrderDto;
import com.helesto.model.OrderEntity;
import com.helesto.socket.OrderSocket;

@ApplicationScoped
public class OrderDao {

    @Inject
    EntityManager em;

    @Inject
	OrderSocket orderSocket;

    public List<OrderEntity> listOrders() {

        TypedQuery<OrderEntity> query = em.createNamedQuery("Orders.findAll", OrderEntity.class);

        return query.getResultList();

    }

    public Optional<OrderEntity> readByClOrdID(int clOrdID) {

        Query query = em.createNamedQuery("Orders.findByClOrdID");

        query.setParameter("clOrdID", clOrdID);

        try {
            return Optional.of((OrderEntity) query.getSingleResult());
        } catch (NoResultException nre) {
            return Optional.empty();
        }

    }

    public void persistOrder(OrderEntity order) {
        em.persist(order);
        orderSocket.broadcast(new OrderDto(order));
    }

    public void updateOrder(OrderEntity order) {
        em.merge(order);
        orderSocket.broadcast(new OrderDto(order));
    }

}
