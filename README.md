# Fix Trading Simulator

- [Overview](#overview)
- [Project Structure](#project-structure)
- [Current version](#current-version)
- [Help Queries](#help-queries)


# Overview

A trading simulator between a Broker and a Stock Exchange using the [Financial Information eXchange (FIX) Protocol](https://www.fixtrading.org/). It's a study project using [Quickfixj](https://www.quickfixj.org/), [Quarkus](https://quarkus.io/), [Angular](https://angular.io/) and [PostgreSQL](https://www.postgresql.org/).

The project idea is to run a Stock Exchange system and allow it to receive connection from Broker instances. Both systems will be build with Quarkus and PostgreSQL on the back-end and Angular on the front-end. The communication will be done with the FIX protocol using Quickfixj.

The Stock Exchange system will be responsible for matching the orders and notifying the brokers. 

The plan is to package each piece of this system in a Docker container and run everything using Docker Compose. So we would have the following containers running:
- Stock exchange:
  - Back-end - Quarkus + QuickFIX/J;
  - Front-end - Angular;
- Two instances of a broker:
  - Back-end - Quarkus + QuickFIX/J;
  - Front-end - Angular;
- PostgreSQL
  - One schema for each back-end.

Although it's an ambitious project, it only has a study objective.

I intend to start it slowly adding a few features at a time.

If you want to join the project, just open an issue!


# Project Structure

[Broker back-end](./broker-back-end/README.md)

[Broker front-end](./broker-front-end/README.md)

[Exchange back-end](./exchange-back-end/README.md)

[Exchange front-end](./exchange-front-end/README.md)

[Documentation](./documentation/README.md)


# Current version

At this moment, only the initial version of the back-end of the broker and the batck-end of the exchange are running. 

You can run them with H2 database (in memory) or PostgreSQL. 

Features:
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

This version will run only the two Quarkus back-end projects, each one with H2 data base (in memory).

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


# Help Queries


```sql
select * from broker.sessions;
select * from broker.messages;
select * from broker.event_log  order by id desc;
select * from broker.messages_log_incoming order by id desc;
select * from broker.messages_log_outgoing order by id desc;

select * from exchange.sessions;
select * from exchange.messages;
select * from exchange.event_log  order by id desc;
select * from exchange.messages_log_incoming order by id desc;
select * from exchange.messages_log_outgoing order by id desc;
```

