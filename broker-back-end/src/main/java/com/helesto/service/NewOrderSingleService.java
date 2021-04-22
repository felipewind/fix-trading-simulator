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
import quickfix.field.Account;
import quickfix.field.ClOrdID;
import quickfix.field.OrdType;
import quickfix.field.OrderQty;
import quickfix.field.Price;
import quickfix.field.SecurityIDSource;
import quickfix.field.Side;
import quickfix.field.Symbol;
import quickfix.field.TimeInForce;
import quickfix.field.TransactTime;
import quickfix.fix44.NewOrderSingle;

@RequestScoped
public class NewOrderSingleService {

	private static final Logger LOG = LoggerFactory.getLogger(NewOrderSingleService.class.getName());

	@Inject
	Trader trader;

	@Inject
	OrderDao orderDao;

	@Transactional(rollbackOn = Exception.class)
	public void newOrderSingle(OrderDto request) throws SessionNotFound {

		SessionID sessionID = trader.getSessionIDFromInitiator();

		// Tag 35 MsgType = D
		NewOrderSingle newOrderSingle = new NewOrderSingle();

		// Tag 11 ClOrdID
		newOrderSingle.set(new ClOrdID("1"));

		// Tag 54 Side
		if (request.getSide() == '1') {
			newOrderSingle.set(new Side(Side.BUY));
		} else {
			newOrderSingle.set(new Side(Side.SELL));
		}

		// Tag 60 TransactTime
		newOrderSingle.set(new TransactTime(LocalDateTime.now()));

		// Tag 40 OrdType
		newOrderSingle.set(new OrdType(OrdType.LIMIT));

		// Tag 38 OrderQty
		newOrderSingle.set(new OrderQty(request.getOrderQty()));

		// Tag 55 Symbol
		if (request.getSymbol() == null) {
			request.setSymbol("IBM");
		}
		newOrderSingle.set(new Symbol(request.getSymbol()));

		// Tag 44 Price
		newOrderSingle.set(new Price(request.getPrice()));

		// Tag 1 Account
		newOrderSingle.setField(new Account("1"));

		// Tag 22 SecurityIDSource
		newOrderSingle.setField(new SecurityIDSource(SecurityIDSource.EXCHANGE_SYMBOL));

		// Tag 59 TimeInForce
		newOrderSingle.setField(new TimeInForce(TimeInForce.DAY));

		insertOrder(request);

		try {
			Session.sendToTarget(newOrderSingle, sessionID);

		} catch (SessionNotFound e) {
			LOG.error(e.getMessage());
			throw e;
		}

	}

	public void insertOrder(OrderDto request) {

		OrderEntity order = new OrderEntity(0, request.getSide(), OrderEntity.NOT_CONFIRMED_BY_COUNTERPARTY,
				request.getSymbol(), request.getPrice(), request.getOrderQty(), 0);

		orderDao.persistOrder(order);

	}

}
