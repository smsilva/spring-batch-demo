package com.github.smsilva.batch.demo;

import com.github.smsilva.batch.demo.config.BatchConfig;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBatchTest
@SpringJUnitConfig
@DataJpaTest
@Import(value = {BatchConfig.class, BatchConfigTest.class})
public class BatchTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BatchTest.class);

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Autowired
    private Job demoJob;

    @Test
    public void jobShouldSuccessfullyComplete() throws Exception {
        this.jobLauncherTestUtils.setJob(demoJob);
        this.jdbcTemplate.update("delete from CUSTOMER");

        for (int i = 1; i <= 10; i++) {
            this.jdbcTemplate.update("insert into CUSTOMER values (?, 0, ?, 100000)",
                    i, "customer" + i);
        }

        JobExecution jobExecution = jobLauncherTestUtils.launchJob();

        assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode());
    }

}
