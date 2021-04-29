package com.helesto.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.helesto.core.Exchange;
import com.helesto.dao.MessagesDao;
import com.helesto.dto.MessageDto;
import com.helesto.util.FixSeparator;

import quickfix.ConfigError;
import quickfix.SessionSettings;

@RequestScoped
public class MessageService {

    @Inject
    Exchange exchange;

    @Inject
    MessagesDao messagesDao;

    public MessageDto[] messageGet() throws ConfigError {

        List<MessageDto> listMessageDto = new ArrayList<>();

        messagesDao.listMessages(exchange.getStringFromSettings(SessionSettings.SENDERCOMPID))
                .forEach(message -> listMessageDto.add(
                        new MessageDto(message.getMsgseqnum(), FixSeparator.putFixSeparator(message.getMessage()))));

        return listMessageDto.toArray(new MessageDto[0]);

    }

}
