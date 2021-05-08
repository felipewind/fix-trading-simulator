package com.helesto.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import com.helesto.dto.OrderDto;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class BrokerService {

    private static final Logger LOG = LoggerFactory.getLogger(BrokerService.class.getName());

    @Inject
    @Channel("broker-orders")
    Emitter<String> emitter;

    @ConfigProperty(name = "api.mqtt.available", defaultValue = "false")
    boolean mqttAvailable;

    public void sendMessageToMQTT(OrderDto orderDto) {

        if (!mqttAvailable) {
            return;
        }

        Jsonb jsonb = JsonbBuilder.create();
        String jsonOrderDto = jsonb.toJson(orderDto);

        LOG.debug("OrderDto sent to MQTT: \n" + jsonOrderDto);

        try {
            emitter.send(jsonOrderDto);
        } catch (Exception e) {
            LOG.error("Error while sending message to MQTT", e);
        }

    }

}
