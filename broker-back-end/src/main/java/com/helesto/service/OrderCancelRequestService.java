package com.helesto.service;

import java.time.LocalDateTime;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.helesto.core.Trader;
import com.helesto.dao.OrderDao;
import com.helesto.dto.OrderDto;
import com.helesto.model.OrderEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import quickfix.Session;
import quickfix.SessionID;
import quickfix.SessionNotFound;
import quickfix.field.ClOrdID;
import quickfix.field.OrderQty;
import quickfix.field.OrigClOrdID;
import quickfix.field.Side;
import quickfix.field.Symbol;
import quickfix.field.TransactTime;
import quickfix.fix44.OrderCancelRequest;

@RequestScoped
public class OrderCancelRequestService {

    private static final Logger LOG = LoggerFactory.getLogger(OrderCancelRequestService.class.getName());

    @Inject
    Trader trader;

    @Inject
    OrderDao orderDao;

    @Transactional(rollbackOn = Exception.class)
    public OrderDto orderCancelRequest(int clOrdID) throws SessionNotFound {

        OrderEntity order = orderDao.readByClOrdID(clOrdID).orElseThrow();

        // Tag 35 MsgType = F
        OrderCancelRequest orderCancelRequest = new OrderCancelRequest();

        // Tag 41 OrigClOrdID
        orderCancelRequest.set(new OrigClOrdID(order.getClOrdID() + ""));

        // Tag 11 ClOrdID
        orderCancelRequest.set(new ClOrdID(order.getClOrdID() + "CANCEL"));

        // Tag 54 Side
        orderCancelRequest.set(new Side(order.getSide()));

        // Tag 60 TransactTime
        orderCancelRequest.set(new TransactTime(LocalDateTime.now()));

        // Tag 38 OrderQty
        orderCancelRequest.set(new OrderQty(order.getOrderQty()));

        // Tag 55 Symbol
        orderCancelRequest.set(new Symbol(order.getSymbol()));

        SessionID sessionID = trader.getSessionIDFromInitiator();

        try {
            Session.sendToTarget(orderCancelRequest, sessionID);

        } catch (SessionNotFound e) {
            LOG.error(e.getMessage());
            throw e;
        }

        return new OrderDto(order);

    }

    public ClOrdID insertOrder(OrderDto request) {

        OrderEntity order = new OrderEntity(0, request.getSide(), OrderEntity.NEW_ORDER_NOT_CONFIRMED,
                request.getSymbol(), request.getPrice(), request.getOrderQty(), 0);

        orderDao.persistOrder(order);

        return new ClOrdID(order.getClOrdID() + "");

    }

}
