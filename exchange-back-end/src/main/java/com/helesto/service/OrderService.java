package com.helesto.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.helesto.core.Exchange;
import com.helesto.dao.OrderDao;
import com.helesto.dto.OrderDto;
import com.helesto.model.OrderEntity;

import quickfix.ConfigError;
import quickfix.SessionNotFound;
import quickfix.field.OrdStatus;

@RequestScoped
public class OrderService {

    @Inject
    OrderDao orderDao;

    @Inject
    ExecutionReportService executionReportService;

    @Inject
    Exchange exchange;

    public OrderDto[] listOrders() throws ConfigError {

        List<OrderDto> listOrderDto = new ArrayList<>();

        orderDao.listOrders().forEach(order -> listOrderDto.add(new OrderDto(order)));

        return listOrderDto.toArray(new OrderDto[0]);

    }

    public OrderDto getOrder(int orderID) throws ConfigError {

        return new OrderDto(orderDao.readByOrderID(orderID).get());

    }

    public OrderDto cancelOrder(int orderID) throws SessionNotFound {

        OrderEntity order = orderDao.readByOrderID(orderID).get();

        order.setOrdStatus(OrdStatus.CANCELED);

        executionReportService.updateOrderAndSendExecutionReport(order, exchange.getSessionIDFromSettings());

        return new OrderDto(order);

    }

    public OrderDto editOrder(int orderID, char ordStatus, double cumQty) throws SessionNotFound {

        OrderEntity order = orderDao.readByOrderID(orderID).get();

        order.setOrdStatus(ordStatus);

        order.setCumQty(cumQty);

        executionReportService.updateOrderAndSendExecutionReport(order, exchange.getSessionIDFromSettings());

        return new OrderDto(order);

    }

}
