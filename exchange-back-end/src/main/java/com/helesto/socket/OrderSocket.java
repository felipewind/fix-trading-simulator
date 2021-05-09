package com.helesto.socket;

import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.helesto.dto.OrderDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ServerEndpoint("/socket/order")
@ApplicationScoped
public class OrderSocket {

    private static final Logger LOG = LoggerFactory.getLogger(OrderSocket.class.getName());

    Set<Session> sessions = new HashSet<>();

    @OnOpen
    public void onOpen(Session session) {
        LOG.debug("onOpen " + session.getId());
        sessions.add(session);
    }

    @OnClose
    public void onClose(Session session) {
        LOG.debug("onClose " + session.getId());
        sessions.remove(session);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        LOG.debug("onError " + session.getId() + " left on error: " + throwable);
        sessions.remove(session);
    }

    @OnMessage
    public void onMessage(String message) {
        LOG.debug("onMessage " + message);
        broadcast(message);
    }

    public void broadcast(OrderDto orderDto) {
        Jsonb jsonb = JsonbBuilder.create();
        String jsonString = jsonb.toJson(orderDto);
        broadcast(jsonString);
    }

    private void broadcast(String message) {
        sessions.forEach(s -> {
            LOG.debug("Message broadcasted: " + message);
            s.getAsyncRemote().sendObject(message, result -> {
                if (result.getException() != null) {
                    LOG.error("Unable to send message: " + result.getException());
                }
            });
        });
    }

}