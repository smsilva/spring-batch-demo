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
public class BatchConfig {

    @Autowired
    private JobRepository repository;

    @Bean
    public Job demoJob(Step firstStep) {
        return new JobBuilder("demoJob", repository)
                .start(firstStep)
                .build();
    }

    @Bean
    public Step firstStep(PlatformTransactionManager transactionManager) {
        return new StepBuilder("demoStep", repository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("Hello, World!");
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }

}
