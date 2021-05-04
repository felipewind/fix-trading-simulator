CREATE SCHEMA IF NOT EXISTS exchange;

CREATE TABLE IF NOT EXISTS exchange.sessions (
  beginstring CHAR(8) NOT NULL,
  sendercompid VARCHAR(64) NOT NULL,
  sendersubid VARCHAR(64) NOT NULL,
  senderlocid VARCHAR(64) NOT NULL,
  targetcompid VARCHAR(64) NOT NULL,
  targetsubid VARCHAR(64) NOT NULL,
  targetlocid VARCHAR(64) NOT NULL,
  session_qualifier VARCHAR(64) NOT NULL,
  creation_time TIMESTAMP NOT NULL,
  incoming_seqnum INTEGER NOT NULL,
  outgoing_seqnum INTEGER NOT NULL,
  PRIMARY KEY (beginstring, sendercompid, sendersubid, senderlocid,
               targetcompid, targetsubid, targetlocid, session_qualifier)
);


CREATE TABLE IF NOT EXISTS exchange.messages (
  beginstring CHAR(8) NOT NULL,
  sendercompid VARCHAR(64) NOT NULL,
  sendersubid VARCHAR(64) NOT NULL,
  senderlocid VARCHAR(64) NOT NULL,
  targetcompid VARCHAR(64) NOT NULL,
  targetsubid VARCHAR(64) NOT NULL,
  targetlocid VARCHAR(64) NOT NULL,
  session_qualifier VARCHAR(64) NOT NULL,
  msgseqnum INTEGER NOT NULL,
  message TEXT NOT NULL,
  PRIMARY KEY (beginstring, sendercompid, sendersubid, senderlocid,
               targetcompid, targetsubid, targetlocid, session_qualifier,
               msgseqnum)
);


CREATE SEQUENCE IF NOT EXISTS exchange.messages_log_sequence_incoming;

CREATE TABLE IF NOT EXISTS exchange.messages_log_incoming (
  id INTEGER DEFAULT NEXTVAL('exchange.messages_log_sequence_incoming'),
  time TIMESTAMP NOT NULL,
  beginstring CHAR(8) NOT NULL,
  sendercompid VARCHAR(64) NOT NULL,
  sendersubid VARCHAR(64) NOT NULL,
  senderlocid VARCHAR(64) NOT NULL,
  targetcompid VARCHAR(64) NOT NULL,
  targetsubid VARCHAR(64) NOT NULL,
  targetlocid VARCHAR(64) NOT NULL,
  session_qualifier VARCHAR(64),
  text TEXT NOT NULL,
  PRIMARY KEY (id)
);



CREATE SEQUENCE IF NOT EXISTS exchange.messages_log_sequence_outgoing;

CREATE TABLE IF NOT EXISTS exchange.messages_log_outgoing (
  id INTEGER DEFAULT NEXTVAL('exchange.messages_log_sequence_outgoing'),
  time TIMESTAMP NOT NULL,
  beginstring CHAR(8) NOT NULL,
  sendercompid VARCHAR(64) NOT NULL,
  sendersubid VARCHAR(64) NOT NULL,
  senderlocid VARCHAR(64) NOT NULL,
  targetcompid VARCHAR(64) NOT NULL,
  targetsubid VARCHAR(64) NOT NULL,
  targetlocid VARCHAR(64) NOT NULL,
  session_qualifier VARCHAR(64),
  text TEXT NOT NULL,
  PRIMARY KEY (id)
);



CREATE SEQUENCE IF NOT EXISTS exchange.event_log_sequence;

CREATE TABLE IF NOT EXISTS exchange.event_log (
  id INTEGER DEFAULT NEXTVAL('exchange.event_log_sequence'),
  time TIMESTAMP NOT NULL,
  beginstring CHAR(8) NOT NULL,
  sendercompid VARCHAR(64) NOT NULL,
  sendersubid VARCHAR(64) NOT NULL,
  senderlocid VARCHAR(64) NOT NULL,
  targetcompid VARCHAR(64) NOT NULL,
  targetsubid VARCHAR(64) NOT NULL,
  targetlocid VARCHAR(64) NOT NULL,
  session_qualifier VARCHAR(64),
  text TEXT NOT NULL,
  PRIMARY KEY (id)
);

CREATE SEQUENCE IF NOT EXISTS exchange.orders_sequence;

create table IF NOT EXISTS exchange.orders (
        OrderID integer DEFAULT NEXTVAL('exchange.orders_sequence'),
        ClOrdID integer,
        OrigClOrdID integer,
        CumQty BIGINT,
        OrdStatus char(1),
        OrderQty BIGINT,
        Price DECIMAL(17,2),
        Side char(1),
        Symbol varchar(20),
        primary key (ClOrdID)
    );

