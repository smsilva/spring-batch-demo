package com.github.smsilva.batch.demo.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class ImprimeOlaTaskLet implements Tasklet {

    private final String nome;

    public ImprimeOlaTaskLet(@Value("#{jobParameters['nome']}") String nome) {
        this.nome = nome;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.printf("Olá, %s!%n", nome);
        return RepeatStatus.FINISHED;
    }

    public String getNome() {
        return nome;
    }

}
