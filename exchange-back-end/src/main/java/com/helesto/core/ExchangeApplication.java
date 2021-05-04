package com.helesto.core;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.helesto.service.ExecutionReportService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import quickfix.Application;
import quickfix.DoNotSend;
import quickfix.FieldNotFound;
import quickfix.IncorrectDataFormat;
import quickfix.IncorrectTagValue;
import quickfix.Message;
import quickfix.MessageCracker;
import quickfix.RejectLogon;
import quickfix.SessionID;
import quickfix.UnsupportedMessageType;

@Singleton
public class ExchangeApplication extends MessageCracker implements Application {

	private static final Logger LOG = LoggerFactory.getLogger(ExchangeApplication.class);

	@Inject
	ExecutionReportService executionReportService;

	public ExchangeApplication() {
		LOG.info("Constructor");
	}

	@Override
	public void onCreate(SessionID sessionID) {
		LOG.info("onCreate");
	}

	@Override
	public void onLogon(SessionID sessionID) {
		LOG.info("onLogon");
	}

	@Override
	public void onLogout(SessionID sessionID) {
		LOG.info("onLogout");
	}

	@Override
	public void toAdmin(Message message, SessionID sessionID) {
		LOG.info("toAdmin");
	}

	@Override
	public void fromAdmin(Message message, SessionID sessionID)
			throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon {
		LOG.info("fromAdmin");
	}

	@Override
	public void toApp(Message message, SessionID sessionID) throws DoNotSend {
		LOG.info("toApp");
	}

	@Override
	public void fromApp(Message message, SessionID sessionID)
			throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
		LOG.info("fromApp");
		crack(message, sessionID);
	}

	public void onMessage(quickfix.fix44.NewOrderSingle newOrderSingle, SessionID sessionID)
			throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
		LOG.info("onMessage quickfix.fix44.NewOrderSingle");
		executionReportService.executionReport(newOrderSingle, sessionID);
	}

	public void onMessage(quickfix.fix44.OrderCancelRequest orderCancelRequest, SessionID sessionID)
			throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
		LOG.info("onMessage quickfix.fix44.OrderCancelRequest");
		executionReportService.executionReport(orderCancelRequest, sessionID);
	}

}
