CREATE TABLE IF NOT EXISTS customers (
  id         BIGSERIAL NOT NULL,
  first_name VARCHAR(50) NOT NULL,
  last_name  VARCHAR(50) NOT NULL,
  name       VARCHAR(100) NOT NULL,
  email      VARCHAR(100) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (email)
);
