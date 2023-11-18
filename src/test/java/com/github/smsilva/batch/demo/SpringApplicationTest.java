package com.github.smsilva.batch.demo;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
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
    Job demoJob;

    @Autowired
    JobLauncherTestUtils jobLauncher;

    @Test
    public void testDemoJob() throws Exception {
        JobParameters jobParameters = jobLauncher.getUniqueJobParametersBuilder()
                .addString("nome", "Sérgio")
                .toJobParameters();

        jobLauncher.setJob(demoJob);
        JobExecution jobExecution = jobLauncher.launchJob(jobParameters);
        assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode());
    }

    @Autowired
    public void clearingDatabase(DataSource dataSource) {
        System.out.println("Clearing database");

        JdbcTemplate template = new JdbcTemplate(dataSource);
        template.update("DELETE FROM customers");

        for (int i = 1; i <= 10; i++) {
            template.update("INSERT INTO customers(id, name) VALUES (?, ?)", i, "customer" + i);
        }
    }

}
