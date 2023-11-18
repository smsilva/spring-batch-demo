package com.github.smsilva.batch.demo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.CommandLineJobRunner;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class AppConfig {

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }

    @Bean
    public JobParameters jobParameters() {
        return new JobParameters();
    }

    @Bean
    public CommandLineJobRunner commandLineJobRunner(JobLauncher jobLauncher, Job imprimeParImparJob, JobParameters jobParameters) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        jobLauncher.run(imprimeParImparJob, jobParameters);
        CommandLineJobRunner commandLineJobRunner = new CommandLineJobRunner();
        commandLineJobRunner.setLauncher(jobLauncher);
        return commandLineJobRunner;
    }

}
