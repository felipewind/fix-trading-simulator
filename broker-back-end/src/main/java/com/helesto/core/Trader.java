package com.helesto.core;

import java.util.Iterator;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.agroal.api.AgroalDataSource;
import quickfix.CompositeLogFactory;
import quickfix.ConfigError;
import quickfix.DefaultMessageFactory;
import quickfix.Initiator;
import quickfix.JdbcLogFactory;
import quickfix.JdbcStoreFactory;
import quickfix.LogFactory;
import quickfix.MessageFactory;
import quickfix.MessageStoreFactory;
import quickfix.ScreenLogFactory;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.SessionSettings;
import quickfix.SocketInitiator;

@Singleton
public class Trader {

    @ConfigProperty(name = "quickfix.appVersion")
    String appVersion;

    @ConfigProperty(name = "quickfix.password")
    String password;

    @ConfigProperty(name = "quickfix.activateScreenLog")
    boolean activateScreenLog;

    @ConfigProperty(name = "quickfix.autoStart")
    boolean autoStart;

    @Inject
    SessionSettingsFactory sessionSettingsFactory;

    @Inject
    AgroalDataSource dataSource;

    @Inject
    TraderApplication traderApplication;

    private static final Logger LOG = LoggerFactory.getLogger(Trader.class.getName());
    private boolean initiatorStarted;
    private SessionSettings sessionSettings;
    private Initiator initiator;

    public Trader() {
        LOG.info("Constructor");
    }

    public void init() {
        LOG.info("init");

        try {

            sessionSettings = sessionSettingsFactory.getSessionSettings();
            LOG.info("SessionSettings created:\n" + sessionSettings.toString());

            MessageStoreFactory messageStoreFactory;

            LogFactory logFactory;

            LOG.info("Initiator creation with database");

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

            initiator = new SocketInitiator(traderApplication, messageStoreFactory, sessionSettings, logFactory,
                    messageFactory);

            LOG.info("Initiator created - SocketInitiator");

            if (autoStart) {
                logon();
            }

        } catch (ConfigError e) {
            LOG.error("ConfigError \n" + e);
            e.printStackTrace();
            if (initiatorStarted) {
                stop();
            }
        }
    }

    public synchronized void logon() throws ConfigError {
        LOG.info("logon");
        if (!initiatorStarted) {
            initiator.start();
            LOG.info("Initiator start - logon solicited \nSessionID created: " + initiator.getSessions() + "\n");
            initiatorStarted = true;
        } else {

            for (SessionID sessionID : initiator.getSessions()) {
                Session.lookupSession(sessionID).logon();
                LOG.info("Logon solicited \nSessionID created: " + initiator.getSessions() + "\n");
            }
        }

    }

    public synchronized void stop() {
        LOG.info("stop");
        if (initiatorStarted) {
            initiator.stop();
            initiatorStarted = false;
        }
    }

    public synchronized void logout() {
        LOG.info("logout");
        for (SessionID sessionId : initiator.getSessions()) {
            Session.lookupSession(sessionId).logout("user requested");
        }
    }

    public SessionID getSessionIDFromInitiator() {
        if (initiatorStarted) {
            return initiator.getSessions().get(0);
        } else {
            throw new RuntimeException("Initiator stopped");
        }
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
        return Session.lookupSession(getSessionIDFromInitiator());
    }

    public boolean isInitiatorStarted() {
        return initiatorStarted;
    }

    public SessionSettings getSessionSettings() {
        return sessionSettings;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public String getPassword() {
        return password;
    }

}