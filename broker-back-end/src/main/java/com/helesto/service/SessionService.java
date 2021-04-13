package com.helesto.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.helesto.core.Trader;
import com.helesto.dto.SessionDto;
import com.helesto.dto.SessionSettingsProperties;
import com.helesto.util.DateUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import quickfix.ConfigError;
import quickfix.SessionID;
import quickfix.SessionSettings;

@RequestScoped
public class SessionService {

    private static final Logger LOG = LoggerFactory.getLogger(SessionService.class.getName());

    @Inject
    Trader trader;

    public SessionDto sessionGet() {

        SessionDto sessionDto = new SessionDto();

        SessionSettings sessionSettings = trader.getSessionSettings();

        sessionDto.setSessionSettingsFile(sessionSettings.toString().split(System.lineSeparator()));

        sessionDto.setInitiatorStarted(trader.isInitiatorStarted());

        Iterator<SessionID> iteratorSessionID = sessionSettings.sectionIterator();

        // Obtaining the SessionID name and parameters
        try {
            while (iteratorSessionID.hasNext()) {
                SessionID sessionID = iteratorSessionID.next();
                sessionDto.setSessionID(sessionID.toString());
                Properties prop = sessionSettings.getSessionProperties(sessionID, true);
                List<SessionSettingsProperties> listSessionSettingsProperties = new ArrayList<>();
                prop.forEach((k, v) -> listSessionSettingsProperties
                        .add(new SessionSettingsProperties(k.toString(), v.toString())));
                sessionDto.setSessionSettingsProperties(
                        listSessionSettingsProperties.toArray(new SessionSettingsProperties[0]));
            }
        } catch (ConfigError e) {
            LOG.error("Error", e);
        }

        if (trader.isInitiatorStarted()) {
            sessionDto.setLoggedOn(trader.getSession().isLoggedOn());

            try {
                sessionDto.setStartTime(DateUtil.dateToLocalDateTime(trader.getSession().getStartTime()).toString());
            } catch (IOException e) {
                sessionDto.setStartTime(null);
            }

        } else {
            sessionDto.setLoggedOn(false);
            sessionDto.setStartTime(null);
        }

        return sessionDto;
    }

    public SessionDto startInitiator() throws ConfigError {

        trader.logon();

        SessionDto sessionDto = sessionGet();

        return sessionDto;

    }

    public SessionDto stopInitiator() throws ConfigError {

        trader.stop();

        SessionDto sessionDto = sessionGet();

        return sessionDto;

    }

}
