package com.github.smsilva.batch.demo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class JobConfig {

    @Autowired
    JobRepository repository;

    @Autowired
    PlatformTransactionManager transactionManager;

    @Bean
    public Job demoJob(Step firstStep) {
        return new JobBuilder("demoJob", repository)
                .start(firstStep)
                .build();
    }

    @Bean
    public Step firstStep() {
        return new StepBuilder("firstStep", repository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("Hello, World!");
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }

}
