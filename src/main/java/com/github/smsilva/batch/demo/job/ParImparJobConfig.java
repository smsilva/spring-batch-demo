package com.github.smsilva.batch.demo.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;
import java.util.stream.IntStream;

@Configuration
public class ParImparJobConfig {

    @Autowired
    JobRepository repository;

    @Autowired
    PlatformTransactionManager transactionManager;

    @Bean
    public Job imprimeParImparJob(Step imprimeParImparStep) {
        return new JobBuilder("imprimeParImparJob", repository)
                .incrementer(new RunIdIncrementer())
                .start(imprimeParImparStep)
                .build();
    }

    @Bean
    public Step imprimeParImparStep() {
        return new StepBuilder("imprimeParImparStep", repository)
                .<Integer, String>chunk(10, transactionManager)
                .reader(contaAteDezReader())
                .processor(parOuImparProcessor())
                .writer(imprimeWriter())
                .build();
    }

    private IteratorItemReader<Integer> contaAteDezReader() {
        List<Integer> numerosDeUmAteDez = IntStream
                .rangeClosed(1, 10)
                .boxed()
                .toList();

        return new IteratorItemReader<>(numerosDeUmAteDez.iterator());
    }

    private FunctionItemProcessor<? super Integer, String> parOuImparProcessor() {
        return new FunctionItemProcessor<Integer, String>(item -> item % 2 == 0 ? String.format("Item %s é Par", item) : String.format("Item %s é Ímpar", item));
    }

    private ItemWriter<String> imprimeWriter() {
        return itens -> itens.forEach(System.out::println);
    }

}
