package com.helesto.service;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.helesto.core.Trader;
import com.helesto.dao.OrderDao;
import com.helesto.model.OrderEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import quickfix.FieldNotFound;
import quickfix.SessionID;
import quickfix.fix44.ExecutionReport;

@ApplicationScoped
public class ExecutionReportService {

	private static final Logger LOG = LoggerFactory.getLogger(ExecutionReportService.class.getName());

	@Inject
	Trader trader;

	@Inject
	OrderDao orderDao;

	@Transactional(rollbackOn = Exception.class)
	public void executionReport(ExecutionReport executionReport, SessionID sessionID) {

		LOG.info("executionReport service");

		try {

			int clOrdID = 0;

			try {
				clOrdID = Integer.valueOf(executionReport.getOrigClOrdID().getValue());
			} catch (FieldNotFound e) {
				clOrdID = Integer.valueOf(executionReport.getClOrdID().getValue());
			}

			Optional<OrderEntity> optionalOrderEntity = orderDao.readByClOrdID(clOrdID);

			if (optionalOrderEntity.isEmpty()) {
				LOG.error("ClOrdID=[" + clOrdID + "] not found in table order");
				return;
			}

			OrderEntity order = optionalOrderEntity.get();

			order.setCumQty(executionReport.getCumQty().getValue());
			order.setOrdStatus(executionReport.getOrdStatus().getValue());

			orderDao.updateOrder(order);

		} catch (FieldNotFound e) {
			LOG.error("FieldNotFound", e);
		}

	}

}
