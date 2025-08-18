DROP TABLE IF EXISTS credit_card;

CREATE TABLE credit_card (
    id bigint not null auto_increment,
    cvv varchar(20),
    expiration_date varchar(20),
    PRIMARY KEY (id)
);DROP TABLE IF EXISTS credit_card_holder;

  CREATE TABLE credit_card_holder (
      id bigint not null auto_increment,
      first_name varchar(30),
      last_name varchar(30),
      zip_code varchar(10),
      PRIMARY KEY (id)
  );