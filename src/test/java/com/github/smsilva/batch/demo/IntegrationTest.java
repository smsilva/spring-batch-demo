package com.github.smsilva.batch.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@JdbcTest
@Sql(scripts = "classpath:db/customers-insert.sql")
public class IntegrationTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void test() {
        assertNotNull(dataSource);
    }

}
