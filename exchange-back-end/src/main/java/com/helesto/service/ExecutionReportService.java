package com.helesto.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.helesto.core.Exchange;
import com.helesto.dao.OrderDao;
import com.helesto.model.OrderEntity;

import org.eclipse.microprofile.faulttolerance.Asynchronous;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import quickfix.FieldNotFound;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.SessionNotFound;
import quickfix.field.AvgPx;
import quickfix.field.ClOrdID;
import quickfix.field.CumQty;
import quickfix.field.ExecID;
import quickfix.field.ExecType;
import quickfix.field.LeavesQty;
import quickfix.field.OrdStatus;
import quickfix.field.OrderID;
import quickfix.field.OrigClOrdID;
import quickfix.field.Side;
import quickfix.field.Symbol;
import quickfix.fix44.ExecutionReport;
import quickfix.fix44.NewOrderSingle;
import quickfix.fix44.OrderCancelRequest;

@ApplicationScoped
public class ExecutionReportService {

	private static final Logger LOG = LoggerFactory.getLogger(ExecutionReportService.class.getName());

	@Inject
	Exchange exchange;

	@Inject
	OrderDao orderDao;

	@Asynchronous
	public Future<String> executionReport(NewOrderSingle newOrderSingle, SessionID sessionID) {

		LOG.info("NewOrderSingle");

		try {

			/**
			 * Return an execution report with OrdStatus = New
			 */

			// Tag 35 MsgType = 8
			ExecutionReport executionReport = new ExecutionReport();

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

			OrderEntity order = insertOrder(newOrderSingle);

			// Tag 37 OrderID
			executionReport.set(new OrderID(order.getOrderID() + ""));

			// Tag 17 ExecID
			executionReport.set(new ExecID(order.getOrderID() + ""));

			Session.sendToTarget(executionReport, sessionID);

			/**
			 * Return an execution report with OrdStatus = Filled
			 */

			Thread.sleep(5000);

			updateOrder(order.getOrigClOrdID(), OrdStatus.FILLED, newOrderSingle.getOrderQty().getValue(), sessionID);

		} catch (Exception e) {
			LOG.error("Error", e);
		}

		return CompletableFuture.completedFuture("End");

	}

	public OrderEntity updateOrder(int origClOrdID, char ordStatus, double cumQty, SessionID sessionID)
			throws SessionNotFound, FieldNotFound, InterruptedException {

		LOG.info("updateOrder origClOrdID=[" + origClOrdID + "]" + " ordStatus=[" + ordStatus + "]" + " cumQty=["
				+ cumQty + "]" + " sessionID=[" + sessionID + "]");

		OrderEntity order = readOrder(origClOrdID);

		// Tag 35 MsgType = 8
		ExecutionReport executionReport = new ExecutionReport();

		// Tag 150 ExecType
		executionReport.set(new ExecType(ExecType.FILL));

		// Tag 39 OrdStatus
		executionReport.set(new OrdStatus(ordStatus));

		// Tag 54 Side
		executionReport.set(new Side(order.getSide()));

		// Tag 151 LeavesQty
		executionReport.set(new LeavesQty(order.getOrderQty() - cumQty));

		// Tag 14 CumQty
		executionReport.set(new CumQty(cumQty));
		order.setCumQty(cumQty);

		// Tag 6 AvgPx
		executionReport.set(new AvgPx(0));

		// Tag 11 ClOrdID
		executionReport.set(new ClOrdID(order.getClOrdID() + ""));

		// Tag 55 Symbol
		executionReport.set(new Symbol(order.getSymbol()));

		// Tag 37 OrderID
		executionReport.set(new OrderID(order.getOrderID() + ""));

		// Tag 17 ExecID
		executionReport.set(new ExecID(order.getOrderID() + ""));

		updateOrder(order);

		Session.sendToTarget(executionReport, sessionID);

		return order;

	}

	@Asynchronous
	public Future<String> executionReport(OrderCancelRequest orderCancelRequest, SessionID sessionID) {

		LOG.info("OrderCancelRequest");

		try {

			cancelOrder(Integer.valueOf(orderCancelRequest.getOrigClOrdID().getValue()),
					orderCancelRequest.getClOrdID().getValue(), OrdStatus.PENDING_CANCEL, sessionID);

			Thread.sleep(5000);

			cancelOrder(Integer.valueOf(orderCancelRequest.getOrigClOrdID().getValue()),
					orderCancelRequest.getClOrdID().getValue(), OrdStatus.CANCELED, sessionID);

		} catch (Exception e) {
			LOG.error("Error", e);
		}

		return CompletableFuture.completedFuture("End");

	}

	public OrderEntity cancelOrder(int origClOrdID, String clOrdID, char ordStatus, SessionID sessionID)
			throws SessionNotFound {

		LOG.info("cancelOrder + origClOrdID=[" + origClOrdID + "]" + " clOrdID=[" + clOrdID + "]" + " ordStatus=["
				+ ordStatus + "]" + " sessionID=[" + sessionID + "]");

		OrderEntity order = readOrder(origClOrdID);

		/**
		 * Return an execution report with OrdStatus = Pending Cancel
		 */

		// Tag 35 MsgType = 8
		ExecutionReport executionReport = new ExecutionReport();

		// Tag 37 OrderID
		executionReport.set(new OrderID(order.getOrderID() + ""));

		// Tag 41 OrigClOrdID
		executionReport.set(new OrigClOrdID(order.getOrigClOrdID() + ""));

		// Tag 11 ClOrdID
		executionReport.set(new ClOrdID(clOrdID + ""));

		// Tag 17 ExecID
		executionReport.set(new ExecID(clOrdID + ""));

		// Tag 150 ExecType
		executionReport.set(new ExecType(ExecType.FILL));

		// Tag 39 OrdStatus
		executionReport.set(new OrdStatus(ordStatus));
		order.setOrdStatus(ordStatus);

		// Tag 54 Side
		executionReport.set(new Side(order.getSide()));

		// Tag 55 Symbol
		executionReport.set(new Symbol(order.getSymbol()));

		// Tag 6 AvgPx
		executionReport.set(new AvgPx(0));

		// Tag 151 LeavesQty
		executionReport.set(new LeavesQty(order.getOrderQty() - order.getCumQty()));

		// Tag 14 CumQty
		executionReport.set(new CumQty(order.getCumQty()));

		updateOrder(order);

		Session.sendToTarget(executionReport, sessionID);

		return order;

	}

	@Transactional(rollbackOn = Exception.class)
	OrderEntity insertOrder(NewOrderSingle newOrderSingle) throws FieldNotFound {

		OrderEntity order = new OrderEntity();

		order.setClOrdID(Integer.valueOf(newOrderSingle.getClOrdID().getValue()));

		order.setOrigClOrdID(Integer.valueOf(newOrderSingle.getClOrdID().getValue()));

		order.setSide(newOrderSingle.getSide().getValue());

		order.setOrdStatus(OrdStatus.NEW);

		order.setSymbol(newOrderSingle.getSymbol().getValue());

		order.setPrice(newOrderSingle.getPrice().getValue());

		order.setOrderQty(newOrderSingle.getOrderQty().getValue());

		order.setCumQty(0);

		orderDao.persistOrder(order);

		return order;

	}

	@Transactional(rollbackOn = Exception.class)
	void updateOrder(OrderEntity order) {

		orderDao.updateOrder(order);

	}

	@Transactional
	OrderEntity readOrder(int origClOrdID) {
		return orderDao.readByOrigClOrdID(origClOrdID).get();
	}

}
