# Fix Trading Simulator

- [Overview](#overview)
- [Current version](#current-version)
- [Project Structure](#project-structure)
- [Milestones](#milestones)

# Overview

A trading simulator between a broker and a stock exchange using the [Financial Information eXchange (FIX) Protocol](https://www.fixtrading.org/). It's a study project using [Quickfixj](https://www.quickfixj.org/), [Quarkus](https://quarkus.io/), [Angular](https://angular.io/) and [PostgreSQL](https://www.postgresql.org/).

The project idea is to run a stock exchange system and allow it to receive connection from broker instances. Both systems will be build with Quarkus and PostgreSQL on the back-end and Angular on the front-end. The communication will be done with the FIX protocol using Quickfixj.

The stock exchange system will be responsible for matching the orders and notifying the brokers. 

The plan is to package each piece of this system in a Docker container and run everything using Docker Compose. So we would have the following containers running:
- Stock exchange:
  - Back-end - Quarkus;
  - Front-end - Angular;
- Two instances of a broker:
  - Back-end - Quarkus;
  - Front-end - Angular;
- PostgreSQL
  - One schema for each back-end.

Although it's an ambitious project, it only has a study objective.

I intend to start it slowly adding a few features at a time.

If you want to join the project, just open an issue!


# Current version

At this moment, only the initial version of the back-end of the broker and the batck-end of the exchange are running. 

You can run them with H2 database (in memory) or PostgreSQL. 

In this scenario:
- `Broker` makes a logon request to `Exchange`;
- They exchange heart beat messages;
- The `Broker` has an endpoint by which you can send a New Single Order to the `Exchange`;
- The `Exchange` automatically executes the orders and respond with an Exeution Report message.


## Exchange

Executes automatically the orders it receives.

Listen on port 8090.

### Swagger

```
localhost:8090/swagger-ui
```
- GET /session-settings
  - View the SessionSettings object parameters


## Broker

Allows you to insert one Order.

Listen on port 8080.

### Swagger

```
localhost:8080/swagger-ui
```
- GET /session-settings
  - View the SessionSettings object parameters

- POST /new-order-single
  - Send and extremely simple NewOrderSingle message


## Running it locally

### With docker-compose

This version will run this containers:
- Broker;
- Exchange;
- PostgreSQL.

Inside the root folder of the project, execute:
```
chmod +x ./run.sh
./run.sh
```

### Without docker-compose

This version will run only the two Quarkus back-end projects, each one with H2 data base.

It's possible to change the `application.properties` and set them to run with PostgreSQL, in this case you should start a PostgreSQL container:
```
docker run -d --name postgres-qfj -p 5432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=postgres postgres
```


1. Enter inside the `exchange-back-end` folder and type:
```
./mvnw compile quarkus:dev -Ddebug=5006
```

2. Enter inside the `broker-back-end` folder and type:
```
./mvnw compile quarkus:dev
```



# Project Structure

[Broker back-end](./broker-back-end/README.md)

[Broker front-end](./broker-front-end/README.md)

[Exchange back-end](./exchange-back-end/README.md)

[Exchange front-end](./exchange-front-end/README.md)

[Documentation](./documentation/README.md)


# Milestones

## 0.1.0

Run with Docker Compose the back-end of the exchange and the back end of the broker, both connected with PostgreSQL. In this first version, there will be only one instance of the broker.

Exchange:
- Exchange **heartbeat** message;
- Receive **New Order Single** message  and return a **Execution Report** message;

Broker:
- Exchange **heartbeat** message;
- Endpoint in swagger-ui to receive a **New Order Single** solicitation;

## 0.2.0

Run two instances of the broker.


## 0.3.0

Create the first version of the front-end of the exchange.


## 0.4.0 

Create the first version of the front-end of the broker.
