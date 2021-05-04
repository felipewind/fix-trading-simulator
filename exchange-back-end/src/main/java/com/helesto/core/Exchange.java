package com.helesto.core;

import java.util.Iterator;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.agroal.api.AgroalDataSource;
import quickfix.Acceptor;
import quickfix.CompositeLogFactory;
import quickfix.ConfigError;
import quickfix.DefaultMessageFactory;
import quickfix.JdbcLogFactory;
import quickfix.JdbcStoreFactory;
import quickfix.LogFactory;
import quickfix.MessageFactory;
import quickfix.MessageStoreFactory;
import quickfix.ScreenLogFactory;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.SessionSettings;
import quickfix.SocketAcceptor;

@Singleton
public class Exchange {

    private static final Logger LOG = LoggerFactory.getLogger(Exchange.class.getName());

    @ConfigProperty(name = "quickfix.activateScreenLog")
    boolean activateScreenLog;

    @ConfigProperty(name = "quickfix.automatic.trade", defaultValue = "false")
    boolean automaticTrade;

    @ConfigProperty(name = "quickfix.automatic.trade.seconds", defaultValue = "5")
    int automaticTradeSeconds;

    @Inject
    SessionSettingsFactory sessionSettingsFactory;

    @Inject
    AgroalDataSource dataSource;

    @Inject
    ExchangeApplication exchangeApplication;

    private SessionSettings sessionSettings;
    private boolean acceptorStarted;
    private Acceptor acceptor;

    public Exchange() {
        LOG.info("Constructor");
    }

    public void init() {

        LOG.info("init");

        try {

            sessionSettings = sessionSettingsFactory.getSessionSettings();
            LOG.info("SessionSettings created:\n" + sessionSettings.toString());

            MessageStoreFactory messageStoreFactory;

            LogFactory logFactory;

            LOG.info("Acceptor creation with database");

            JdbcStoreFactory jdbcStoreFactory = new JdbcStoreFactory(sessionSettings);

            jdbcStoreFactory.setDataSource(dataSource);

            messageStoreFactory = jdbcStoreFactory;
            LOG.info("MessageStoreFactory created - JdbcStoreFactory");

            JdbcLogFactory jdbcLogFactory = new JdbcLogFactory(sessionSettings);

            jdbcLogFactory.setDataSource(dataSource);

            if (activateScreenLog) {
                logFactory = new CompositeLogFactory(
                        new LogFactory[] { new ScreenLogFactory(sessionSettings), jdbcLogFactory });
                LOG.info("LogFactory created - JdbcLogFactory and ScreenLogFactory");
            } else {
                logFactory = jdbcLogFactory;
                LOG.info("LogFactory created - JdbcLogFactory");
            }

            MessageFactory messageFactory = new DefaultMessageFactory();
            LOG.info("MessageFactory created - DefaultMessageFactory");

            acceptor = new SocketAcceptor(exchangeApplication, messageStoreFactory, sessionSettings, logFactory,
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
        if (acceptorStarted) {
            acceptor.stop();
            acceptorStarted = false;
        }
    }

    public SessionSettings getSessionSettings() {
        return sessionSettings;
    }

    public boolean isAcceptorStarted() {
        return acceptorStarted;
    }

    public SessionID getSessionIDFromSettings() {

        Iterator<SessionID> iteratorSessionID = sessionSettings.sectionIterator();

        SessionID sessionID = null;

        while (iteratorSessionID.hasNext()) {
            sessionID = iteratorSessionID.next();
        }

        return sessionID;
    }

    public String getStringFromSettings(String key) throws ConfigError {
        return sessionSettings.getString(getSessionIDFromSettings(), key);
    }

    public Session getSession() {
        return Session.lookupSession(getSessionIDFromAcceptor());
    }

    public SessionID getSessionIDFromAcceptor() {
        if (acceptorStarted) {
            return acceptor.getSessions().get(0);
        } else {
            throw new RuntimeException("Acceptor stopped");
        }
    }

    public boolean isAutomaticTrade() {
        return automaticTrade;
    }

    public void setAutomaticTrade(boolean automaticTrade) {
        this.automaticTrade = automaticTrade;
    }

    public int getAutomaticTradeSeconds() {
        return automaticTradeSeconds;
    }

    public void setAutomaticTradeSeconds(int automaticTradeSeconds) {
        this.automaticTradeSeconds = automaticTradeSeconds;
    }

}