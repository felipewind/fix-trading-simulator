package com.helesto.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.helesto.core.Trader;
import com.helesto.dao.EventLogDao;
import com.helesto.dto.EventLogDto;

import quickfix.ConfigError;
import quickfix.SessionSettings;

@RequestScoped
public class EventLogService {

    @Inject
    Trader trader;

    @Inject
    EventLogDao eventLogDao;

    public EventLogDto[] eventLogGet() throws ConfigError {

        List<EventLogDto> listEventLogDto = new ArrayList<>();

        eventLogDao.listEventLog(trader.getStringFromSettings(SessionSettings.SENDERCOMPID))
                .forEach(eventLog -> listEventLogDto
                        .add(new EventLogDto(eventLog.getId(), eventLog.getTime().toString(), eventLog.getText())));

        return listEventLogDto.toArray(new EventLogDto[0]);

    }

}
