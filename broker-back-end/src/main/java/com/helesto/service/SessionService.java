package com.helesto.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.helesto.core.Trader;
import com.helesto.dao.SessionsDao;
import com.helesto.dto.SessionDto;
import com.helesto.dto.SessionDto.SessionSettingsProperties;
import com.helesto.dto.SessionDto.SessionStorage;
import com.helesto.model.SessionsEntity;
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

    @Inject
    SessionsDao sessionsDao;

    public SessionDto sessionGet() {

        SessionDto sessionDto = new SessionDto();

        SessionSettings sessionSettings = trader.getSessionSettings();

        sessionDto.setSessionSettingsFile(sessionSettings.toString().split(System.lineSeparator()));

        sessionDto.setInitiatorStarted(trader.isInitiatorStarted());

        SessionID sessionID = trader.getSessionIDFromSettings();

        sessionDto.setSessionID(sessionID.toString());

        try {
            // Obtaining the Session properties
            Properties prop = sessionSettings.getSessionProperties(sessionID, true);
            List<SessionSettingsProperties> listSessionSettingsProperties = new ArrayList<>();
            prop.forEach((k, v) -> listSessionSettingsProperties
                    .add(new SessionSettingsProperties(k.toString(), v.toString())));
            sessionDto.setSessionSettingsProperties(
                    listSessionSettingsProperties.toArray(new SessionSettingsProperties[0]));

            // Obtaining the Session Storage information
            SessionStorage sessionStorage = new SessionStorage();
            Optional<SessionsEntity> optionalSessionEntity = sessionsDao
                    .readSession(trader.getStringFromSettings(SessionSettings.SENDERCOMPID));
            if (optionalSessionEntity.isPresent()) {
                SessionsEntity sessionEntity = optionalSessionEntity.get();
                sessionStorage.setCreationTime(sessionEntity.getCreation_time().toString());
                sessionStorage.setIncomingSeqnum(sessionEntity.getIncoming_seqnum());
                sessionStorage.setOutgoingSeqnum(sessionEntity.getOutgoing_seqnum());
            }
            sessionDto.setSessionStorage(sessionStorage);

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

    public SessionDto logout() throws ConfigError {

        trader.logout();

        SessionDto sessionDto = sessionGet();

        return sessionDto;

    }

}
