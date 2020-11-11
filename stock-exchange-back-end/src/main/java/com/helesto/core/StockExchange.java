package com.helesto.core;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.agroal.api.AgroalDataSource;
import quickfix.Acceptor;
import quickfix.ConfigError;
import quickfix.DefaultMessageFactory;
import quickfix.JdbcLogFactory;
import quickfix.JdbcStoreFactory;
import quickfix.LogFactory;
import quickfix.MessageFactory;
import quickfix.MessageStoreFactory;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.SessionSettings;
import quickfix.SocketAcceptor;

@Singleton
public class StockExchange {

    private static final Logger LOG = LoggerFactory.getLogger(StockExchange.class.getName());

    @Inject
    SessionSettingsFactory sessionSettingsFactory;

    @Inject
    AgroalDataSource dataSource;

    @Inject
    StockExchangeApplication stockExchangeApplication;

    private SessionSettings sessionSettings;
    private boolean acceptorStarted;
    private Acceptor acceptor;

    public StockExchange() {
        LOG.info("Constructor");
    }

    public void init() {

        LOG.info("init");

        try {

            sessionSettings = sessionSettingsFactory.getSessionSettings();
            LOG.info("SessionSettings created:\n" + sessionSettings.toString());

            JdbcStoreFactory jdbcStoreFactory = new JdbcStoreFactory(sessionSettings);

            jdbcStoreFactory.setDataSource(dataSource);

            MessageStoreFactory messageStoreFactory = jdbcStoreFactory;
            LOG.info("MessageStoreFactory created - JdbcStoreFactory");

            JdbcLogFactory jdbcLogFactory = new JdbcLogFactory(sessionSettings);

            jdbcLogFactory.setDataSource(dataSource);

            LogFactory logFactory = jdbcLogFactory;
            LOG.info("LogFactory created - JdbcLogFactory");

            MessageFactory messageFactory = new DefaultMessageFactory();
            LOG.info("MessageFactory created - DefaultMessageFactory");

            acceptor = new SocketAcceptor(stockExchangeApplication, messageStoreFactory, sessionSettings, logFactory,
                    messageFactory);
            LOG.info("Acceptor created - SocketAcceptor");

            start();

        } catch (ConfigError e) {
            LOG.error("ConfigError" + e);
            e.printStackTrace();
            if (acceptorStarted) {
                stop();
            }
        }

    }

    public synchronized void start() throws ConfigError {
        LOG.info("start");
        if (!acceptorStarted) {
            acceptor.start();
            LOG.info("Acceptor start - \nSessionID created: " + acceptor.getSessions() + "\n");
            acceptorStarted = true;
        } else {
            for (SessionID sessionID : acceptor.getSessions()) {
                Session.lookupSession(sessionID).logon();
                LOG.info("Logon solicited \nSessionID created: " + acceptor.getSessions() + "\n");
            }
        }

    }

    public synchronized void stop() {
        LOG.info("stop");
        if (!acceptorStarted) {
            acceptor.stop();
            acceptorStarted = false;
        }
    }

    public SessionSettings getSessionSettings() {
        return sessionSettings;
    }

}