CREATE TABLE IF NOT EXISTS customers (
  id      BIGSERIAL    NOT NULL,
  name    VARCHAR(100) NOT NULL,
  email   VARCHAR(100),
  PRIMARY KEY (id),
  UNIQUE (email)
);
