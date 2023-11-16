package com.github.smsilva.batch.demo;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@SpringBatchTest
class SpringApplicationTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    public void clearingDatabase(DataSource dataSource) {
        System.out.println("Clearing database");

        JdbcTemplate template = new JdbcTemplate(dataSource);
        template.update("DELETE FROM customers");

        for (int i = 1; i <= 10; i++) {
            template.update("INSERT INTO customers(id, name) VALUES (?, ?)", i, "customer" + i);
        }
    }

    @Test
    public void testJob(@Autowired Job demoJob) throws Exception {
        this.jobLauncherTestUtils.setJob(demoJob);

        JobExecution jobExecution = jobLauncherTestUtils.launchJob();

        assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode());
    }

}
