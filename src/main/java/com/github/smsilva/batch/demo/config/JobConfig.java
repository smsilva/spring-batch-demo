package com.github.smsilva.batch.demo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
                .tasklet(imprimeOlaTaskLet(null), transactionManager)
                .build();
    }

    @Bean
    @StepScope
    public Tasklet imprimeOlaTaskLet(@Value("#{jobParameters['nome']}") String nome) {
        return (contribution, chunkContext) -> {
            System.out.printf("Olá, %s!%n", nome);
            return RepeatStatus.FINISHED;
        };
    }

}
