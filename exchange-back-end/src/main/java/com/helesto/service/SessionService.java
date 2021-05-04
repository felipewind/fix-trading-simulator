package com.helesto.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.helesto.core.Exchange;
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
    Exchange exchange;

    @Inject
    SessionsDao sessionsDao;

    public SessionDto sessionGet() {

        SessionDto sessionDto = new SessionDto();

        SessionSettings sessionSettings = exchange.getSessionSettings();

        sessionDto.setSessionSettingsFile(sessionSettings.toString().split(System.lineSeparator()));

        sessionDto.setAcceptorStarted(exchange.isAcceptorStarted());

        SessionID sessionID = exchange.getSessionIDFromSettings();

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
                    .readSession(exchange.getStringFromSettings(SessionSettings.SENDERCOMPID));
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

        if (exchange.isAcceptorStarted()) {
            sessionDto.setLoggedOn(exchange.getSession().isLoggedOn());

            try {
                sessionDto.setStartTime(DateUtil.dateToLocalDateTime(exchange.getSession().getStartTime()).toString());
            } catch (IOException e) {
                sessionDto.setStartTime(null);
            }

        } else {
            sessionDto.setLoggedOn(false);
            sessionDto.setStartTime(null);
        }

        return sessionDto;
    }

    public SessionDto startAcceptor() throws ConfigError {

        exchange.start();

        SessionDto sessionDto = sessionGet();

        return sessionDto;

    }

    public SessionDto stopAcceptor() throws ConfigError {

        exchange.stop();

        SessionDto sessionDto = sessionGet();

        return sessionDto;

    }

}
