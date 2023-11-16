package com.github.smsilva.batch.demo;

import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchConfigTest {

    @Bean
    public JobRepository jobRegistry(JobRepositoryFactoryBean jobRepositoryFactoryBean) throws Exception {
        return jobRepositoryFactoryBean.getObject();
    }
}