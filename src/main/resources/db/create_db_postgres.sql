DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS messages;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS account_histories;

DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 1;

CREATE TABLE users
(
  id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  login VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  registered TIMESTAMP DEFAULT now(),
  enabled BOOLEAN DEFAULT TRUE,
  role VARCHAR(20) NOT NULL
);
CREATE UNIQUE INDEX users_unique_login_idx ON USERS ( login );

CREATE TABLE messages
(
  id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  time TIMESTAMP DEFAULT now(),
  subject VARCHAR,
  text TEXT,
  new BOOLEAN DEFAULT TRUE,
  user_id INTEGER NOT NULL,
  FOREIGN KEY ( user_id ) REFERENCES USERS ( id ) ON DELETE CASCADE
);

CREATE TABLE account_histories
(
  id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  from_user_id INTEGER NOT NULL,
  to_user_id INTEGER NOT NULL,
  from_account_id INTEGER NOT NULL,
  to_account_id INTEGER NOT NULL,
  commission_account_id INTEGER NOT NULL,
  operation_time TIMESTAMP,
  sender_currency VARCHAR(3) NOT NULL,
  recipient_currency VARCHAR(3) NOT NULL,
  from_account_number VARCHAR(255) NOT NULL,
  to_account_number VARCHAR(255) NOT NULL,
  comment VARCHAR(255) NOT NULL ,
  sender_amount DECIMAL(20,2) NOT NULL,
  recipient_amount DECIMAL(20,2) NOT NULL,
  commission DECIMAL(20,2) NOT NULL,
  amount_after_operation_on_sender DECIMAL(20,2) NOT NULL,
  amount_after_operation_in_recipient DECIMAL(20,2) NOT NULL
);

CREATE TABLE accounts
(
  id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name VARCHAR(255) NOT NULL,
  account_number VARCHAR(255),
  currency VARCHAR(3),
  balance DECIMAL(20,2) NOT NULL,
  user_id INTEGER NOT NULL,
  enabled BOOLEAN DEFAULT TRUE,
  FOREIGN KEY ( user_id ) REFERENCES USERS ( id ) ON DELETE CASCADE
);
CREATE UNIQUE INDEX account_number_idx ON accounts(account_number);
