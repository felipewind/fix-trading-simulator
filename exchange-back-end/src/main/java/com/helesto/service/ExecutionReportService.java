package com.helesto.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.helesto.core.Exchange;

import org.eclipse.microprofile.faulttolerance.Asynchronous;
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
	Exchange exchange;

	@Asynchronous
	public Future<String> executionReport(NewOrderSingle newOrderSingle, SessionID sessionID) {

		LOG.info("executionReport - NewOrderSingle");

		try {

			// Return an execution report with OrdStatus = New

			// Tag 35 MsgType = 8
			ExecutionReport executionReport = new ExecutionReport();

			// Tag 37 OrderID
			executionReport.set(new OrderID(newOrderSingle.getClOrdID().getValue()));

			// Tag 11 ExecID
			executionReport.set(new ExecID(newOrderSingle.getClOrdID().getValue()));

			// Tag 150 ExecType
			executionReport.set(new ExecType(ExecType.FILL));

			// Tag 39 OrdStatus
			executionReport.set(new OrdStatus(OrdStatus.NEW));

			// Tag 54 Side
			executionReport.set(newOrderSingle.getSide());

			// Tag 151 LeavesQty
			executionReport.set(new LeavesQty(newOrderSingle.getOrderQty().getValue()));

			// Tag 14 CumQty
			executionReport.set(new CumQty(0));

			// Tag 6 AvgPx
			executionReport.set(new AvgPx(0));

			// Tag 11 ClOrdID
			executionReport.set(newOrderSingle.getClOrdID());

			// Tag 55 Symbol
			executionReport.set(newOrderSingle.getSymbol());

			Session.sendToTarget(executionReport, sessionID);

			// Return an execution report with OrdStatus = Filled
			Thread.sleep(5000);

			// Tag 39 OrdStatus
			executionReport.set(new OrdStatus(OrdStatus.FILLED));

			// Tag 151 LeavesQty
			executionReport.set(new LeavesQty(0));

			// Tag 14 CumQty
			executionReport.set(new CumQty(newOrderSingle.getOrderQty().getValue()));

			// Tag 6 AvgPx
			executionReport.set(new AvgPx(newOrderSingle.getPrice().getValue()));

			Session.sendToTarget(executionReport, sessionID);

		} catch (SessionNotFound e) {
			LOG.error(e.getMessage());
		} catch (FieldNotFound e) {
			LOG.error(e.getMessage());
		} catch (InterruptedException e) {
			LOG.error(e.getMessage());
		}

		return CompletableFuture.completedFuture("End");

	}

}
