# Fix Trading Simulator

- [Overview](#overview)
- [Project Structure](#project-structure)
- [Milestones](#milestones)

# Overview

A trading simulator between a broker and a stock exchange using the [Financial Information eXchange (FIX) Protocol](https://www.fixtrading.org/). It's a study project using [Quickfixj](https://www.quickfixj.org/), [Quarkus](https://quarkus.io/), [Angular](https://angular.io/) and [PostgreSQL](https://www.postgresql.org/).

The project idea is to run a stock exchange system and allow it to receive connection from broker instances. Both systems will be build with Quarkus and PostgreSQL on the back-end and Angular on the front-end. The communication will be done with the FIX protocol using Quickfixj.

The stock exchange system will be responsible for matching the orders and notifying the brokers. 

The plan is to package each piece of this system in a Docker container and run everything usind Docker Compose. So we would have the following containers running:
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


# Project Structure

Broker back-end

Broker front-end

Stock exchange back-end

Stock exchange front-end

Documentation


# Milestones

## 0.1.0

Run with Docker Compose the back-end of the stock exchange and the back end of the broker, both connected with PostgreSQL. In this first version, there will be only one instance of the broker.

Stock-exchange:
- Exchange **heartbeat** message;
- Receive **New Order Single** message  and return a **Execution Report** message;

Broker:
- Exchange **heartbeat** message;
- Endpoint in swagger-ui to receive a **New Order Single** solicitation;

## 0.2.0

Run two instances of the broker.


## 0.3.0

Create the first version of the front-end of the stock exchange.


## 0.4.0 

Create the first version of the front-end of the broker.
