package com.github.smsilva.batch.demo.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

@Component
public class FirstStepConfig {

    private final JobRepository repository;
    private final PlatformTransactionManager transactionManager;

    public FirstStepConfig(JobRepository repository, PlatformTransactionManager transactionManager) {
        this.repository = repository;
        this.transactionManager = transactionManager;
    }

    @Bean
    public Step firstStep(Tasklet imprimeOlaTaskLet) {
        return new StepBuilder("firstStep", repository)
                .tasklet(imprimeOlaTaskLet, transactionManager)
                .build();
    }

}
