CREATE TABLE IF NOT EXISTS customers (
  id         BIGSERIAL NOT NULL,
  first_name VARCHAR(50) NOT NULL,
  last_name  VARCHAR(50) NOT NULL,
  name       VARCHAR(100) NOT NULL,
  email      VARCHAR(100) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (email)
);

INSERT INTO customers(id, first_name, last_name, name, email) VALUES (1, 'John', 'Doe', 'John Doe', 'john.doe@example.com');
INSERT INTO customers(id, first_name, last_name, name, email) VALUES (2, 'Jane', 'Doe', 'Jane Doe', 'jane.doe@example.com');
INSERT INTO customers(id, first_name, last_name, name, email) VALUES (3, 'Richard', 'Doe', 'Richard Doe', 'richard.doe@example.com');
