CREATE TABLE IF NOT EXISTS customers (
  id    BIGSERIAL    NOT NULL,
  name  VARCHAR(100) NOT NULL,
  email VARCHAR(100),
  PRIMARY KEY (id),
  UNIQUE (email)
);

INSERT INTO customers(id, name, email) VALUES (1, 'John Doe'   , 'john.doe@example.com'   );
INSERT INTO customers(id, name, email) VALUES (2, 'Jane Doe'   , 'jane.doe@example.com'   );
INSERT INTO customers(id, name, email) VALUES (3, 'Richard Doe', 'richard.doe@example.com');
