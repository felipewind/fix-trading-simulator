# Real Time update of the front end information


# MQTT

[wikipedia](https://en.wikipedia.org/wiki/MQTT)

> The Message Queuing Telemetry Transport (MQTT) is a lightweight, publish-subscribe network protocol that transports messages between devices. The protocol usually runs over TCP/IP; however, any network protocol that provides ordered, lossless, bi-directional connections can support MQTT.[1] It is designed for connections with remote locations where a "small code footprint" is required or the network bandwidth is limited. The protocol is an open OASIS standard and an ISO recommendation (ISO/IEC 20922). 

# Mosquitto

[eclipse mosquitto](https://mosquitto.org/)

> Eclipse Mosquitto is an open source (EPL/EDL licensed) message broker that implements the MQTT protocol versions 5.0, 3.1.1 and 3.1. Mosquitto is lightweight and is suitable for use on all devices from low power single board computers to full servers.

## Container

[Mosquitto DockerHub](https://hub.docker.com/_/eclipse-mosquitto)

```
$ docker run -it -p 1883:1883 -p 9001:9001 eclipse-mosquitto:1.6.2
```


# Quarkus

[Quarkus MQTT Quickstart](https://github.com/quarkusio/quarkus-quickstarts/blob/main/mqtt-quickstart/README.md)


# SmallRye Reactive Messaging

[SmallRye Reactive Messaging](https://smallrye.io/smallrye-reactive-messaging/smallrye-reactive-messaging/2/mqtt/mqtt.html)

> The MQTT connector adds support for MQTT to Reactive Messaging.
> It lets you receive messages from an MQTT server or broker as well as send MQTT messages. The MQTT connector is based on the Vert.x MQTT Client.



https://smallrye.io/smallrye-reactive-messaging/smallrye-reactive-messaging/3.2/index.html



