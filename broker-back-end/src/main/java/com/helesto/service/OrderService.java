package com.helesto.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.helesto.dao.OrderDao;
import com.helesto.dto.OrderDto;

import quickfix.ConfigError;

@RequestScoped
public class OrderService {

    @Inject
    OrderDao orderDao;

    public OrderDto[] listOrders() throws ConfigError {

        List<OrderDto> listOrderDto = new ArrayList<>();

        orderDao.listOrders().forEach(order -> listOrderDto.add(new OrderDto(order.getClOrdID(), order.getSide(),
                order.getOrdStatus(), order.getSymbol(), order.getPrice(), order.getOrderQty(), order.getCumQty())));

        return listOrderDto.toArray(new OrderDto[0]);

    }

}
