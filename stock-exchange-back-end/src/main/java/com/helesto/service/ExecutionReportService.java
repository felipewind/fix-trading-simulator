package com.helesto.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.helesto.core.StockExchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import quickfix.FieldNotFound;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.SessionNotFound;
import quickfix.field.AvgPx;
import quickfix.field.CumQty;
import quickfix.field.ExecID;
import quickfix.field.ExecType;
import quickfix.field.LeavesQty;
import quickfix.field.OrdStatus;
import quickfix.field.OrderID;
import quickfix.fix44.ExecutionReport;
import quickfix.fix44.NewOrderSingle;

@ApplicationScoped
public class ExecutionReportService {

	private static final Logger LOG = LoggerFactory.getLogger(ExecutionReportService.class.getName());

	@Inject
	StockExchange stockExchange;

	public void executionReport(NewOrderSingle newOrderSingle, SessionID sessionID) {

		LOG.info("executionReport - NewOrderSingle");

		try {

			// Tag 35 MsgType = 8
			ExecutionReport executionReport = new ExecutionReport();

			// Tag 37 OrderID
			executionReport.set(new OrderID("1"));

			// Tag 11 Execution ID
			executionReport.set(new ExecID("1"));

			// Tag 150 ExecType
			executionReport.set(new ExecType(ExecType.FILL));

			// Tag 39 OrdStatus
			executionReport.set(new OrdStatus(OrdStatus.FILLED));

			// Tag 54 Side
			executionReport.set(newOrderSingle.getSide());

			// Tag 151 LeavesQty
			executionReport.set(new LeavesQty(newOrderSingle.getOrderQty().getValue()));

			// Tag 14 CumQty
			executionReport.set(new CumQty(0));

			// Tag 6 AvgPx
			executionReport.set(new AvgPx(newOrderSingle.getPrice().getValue()));

			// Tag 55 Symbol
			executionReport.set(newOrderSingle.getSymbol());

			Session.sendToTarget(executionReport, sessionID);

		} catch (SessionNotFound e) {
			LOG.error(e.getMessage());
		} catch (FieldNotFound e) {
			LOG.error(e.getMessage());
		}

	}

}
