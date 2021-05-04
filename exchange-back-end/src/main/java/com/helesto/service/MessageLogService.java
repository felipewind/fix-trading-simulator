package com.helesto.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.helesto.core.Exchange;
import com.helesto.dao.MessageLogDao;
import com.helesto.dto.MessageLogDto;
import com.helesto.util.FixSeparator;

import quickfix.ConfigError;
import quickfix.SessionSettings;

@RequestScoped
public class MessageLogService {

    public static final String INCOMING = "INCOMING";

    public static final String OUTGOING = "OUTGONIG";

    @Inject
    Exchange exchange;

    @Inject
    MessageLogDao messageLogDao;

    public MessageLogDto[] messageLogGet(String typeOfLog) throws ConfigError {

        List<MessageLogDto> listMessageLogDto = new ArrayList<>();

        if (typeOfLog.equals(INCOMING)) {
            messageLogDao.listMessageLogIncoming(exchange.getStringFromSettings(SessionSettings.SENDERCOMPID))
                    .forEach(messageLog -> listMessageLogDto.add(new MessageLogDto(messageLog.getId(),
                            messageLog.getTime().toString(), FixSeparator.putFixSeparator(messageLog.getText()))));
        } else {
            messageLogDao.listMessageLogOutgoing(exchange.getStringFromSettings(SessionSettings.SENDERCOMPID))
                    .forEach(messageLog -> listMessageLogDto.add(new MessageLogDto(messageLog.getId(),
                            messageLog.getTime().toString(), FixSeparator.putFixSeparator(messageLog.getText()))));
        }

        return listMessageLogDto.toArray(new MessageLogDto[0]);

    }

}
