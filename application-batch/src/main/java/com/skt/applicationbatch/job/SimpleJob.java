package com.skt.applicationbatch.job;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class SimpleJob {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public Job simpleJobBean() {
        return new JobBuilder("simpleJob", jobRepository)
                .start(simpleStepBean())
                .build();
    }

    @Bean
    public Step simpleStepBean() {
        return new StepBuilder("simpleStep", jobRepository)
                .tasklet(helloWorldTasklet(), transactionManager)
                .build();
    }

    @Bean
    public Tasklet helloWorldTasklet() {
        return (contribution, chunkContext) -> {
            System.out.println("Hello, Spring Batch!");
            return RepeatStatus.FINISHED;
        };
    }
}
