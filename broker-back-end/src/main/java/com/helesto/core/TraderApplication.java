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
import quickfix.LogUtil;
import quickfix.Message;
import quickfix.MessageCracker;
import quickfix.MessageUtils;
import quickfix.RejectLogon;
import quickfix.SessionID;
import quickfix.UnsupportedMessageType;
import quickfix.field.MsgType;
import quickfix.field.RawData;
import quickfix.field.RawDataLength;

@Singleton
public class TraderApplication extends MessageCracker implements Application {

	private static final Logger LOG = LoggerFactory.getLogger(TraderApplication.class);

	@Inject
	Bootstrap bootstrap;

	@Inject
	ExecutionReportService executionReportService;

	public TraderApplication() {
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
		if (isMessageOfType(message, MsgType.LOGON)) {
			addLogonField(message, sessionID);
		}
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
		try {
			crack(message, sessionID);
		} catch (UnsupportedMessageType e) {
			LOG.error("UnsupportedMessageType \n" + message.toRawString() + "\n", e);
		}
	}

	private void addLogonField(Message message, SessionID sessionID) {
		// Tag 95 RawDataLength
		message.getHeader().setField(new RawDataLength(bootstrap.getTrader().getPassword().length()));

		// Tag 96 RawData
		message.getHeader().setField(new RawData(bootstrap.getTrader().getPassword()));

		// Tag 58 Text
		// message.getHeader().setField(new
		// Text(bootstrap.getTrader().getAppVersion()));

	}

	private boolean isMessageOfType(Message message, String type) {
		try {
			return type.equals(message.getHeader().getField(new MsgType()).getValue());
		} catch (FieldNotFound e) {
			logErrorToSessionLog(message, e);
			return false;
		}
	}

	private void logErrorToSessionLog(Message message, FieldNotFound e) {
		LogUtil.logThrowable(MessageUtils.getSessionID(message), e.getMessage(), e);
	}

	public void onMessage(quickfix.fix44.ExecutionReport executionReport, SessionID sessionID)
			throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
		LOG.info("onMessage quickfix.fix44.ExecutionReport");
		executionReportService.executionReport(executionReport, sessionID);

	}

}
