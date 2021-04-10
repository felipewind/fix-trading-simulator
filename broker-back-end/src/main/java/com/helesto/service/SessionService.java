package com.helesto.service;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.helesto.core.Trader;
import com.helesto.dto.SessionDto;
import com.helesto.util.DateUtil;

import quickfix.ConfigError;

@RequestScoped
public class SessionService {

    @Inject
    Trader trader;

    public SessionDto sessionGet() {

        SessionDto sessionDto = new SessionDto();

        sessionDto.setSettings(trader.getSessionSettings().toString().split(System.lineSeparator()));

        sessionDto.setInitiatorStarted(trader.isInitiatorStarted());

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
