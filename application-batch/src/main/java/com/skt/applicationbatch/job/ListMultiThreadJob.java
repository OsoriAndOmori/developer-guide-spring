package com.skt.applicationbatch.job;

import com.skt.applicationbatch.component.ListItemStreamReader;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.item.support.builder.SynchronizedItemStreamReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Arrays;
import java.util.List;


@Configuration
@RequiredArgsConstructor
public class ListMultiThreadJob {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public Job multiThreadJob() {
        return new JobBuilder("multiThreadJob", jobRepository)
                .start(multiThreadStep())
                .build();
    }

    @Bean
    public Step multiThreadStep() {
        return new StepBuilder("multiThreadStep", jobRepository)
                .<String, String>chunk(2, transactionManager) // 청크 크기
                .reader(listItemReader())
                .processor(itemProcessor())
                .writer(itemWriter())
//                .taskExecutor(taskExecutor()) // 멀티스레드 실행
                .taskExecutor(threadPoolTaskExecutor()) // 멀티스레드 실행
                .transactionManager(transactionManager) // 트랜잭션 매니저 설정
                .build();
    }

    private static final List<String> items = Arrays.asList("Spring", "Boot", "Batch", "Multi", "Thread", "Example", "tesdfsd", "sdkmflsf", "sldqpwo123");

    @Bean
    public ListItemReader<String> listItemReader() {
        return new ListItemReader<>(items);
    }

    @Bean
    public ItemReader<String> listItemStreamReader() {
        ListItemStreamReader<String> reader = new ListItemStreamReader<>(items);
        return new SynchronizedItemStreamReaderBuilder<String>()
                .delegate(reader)
                .build();
    }

    @Bean
    public ItemProcessor<String, String> itemProcessor() {
        return item -> {
//            if(item.equals("Example")){
//                throw new RuntimeException("hello crash");
//            }
            System.out.println("[Batch] Processing item: " + item + " by thread: " + Thread.currentThread().getName());
            return item.toUpperCase();
        };
    }

    @Bean
    public ItemWriter<String> itemWriter() {
        return items -> {
            System.out.println("[Batch] Writing items: " + items + " by thread: " + Thread.currentThread().getName());
        };
    }

    @Bean
    public SimpleAsyncTaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor executor = new SimpleAsyncTaskExecutor("spring-batch-");
        executor.setConcurrencyLimit(2);  // 최대 동시 실행 스레드 수 설정
        return executor;
    }

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3);  // 기본 스레드 수
        executor.setMaxPoolSize(3);   // 최대 스레드 수
        executor.setQueueCapacity(25);  // 대기 큐 용량
        executor.setThreadNamePrefix("spring-batch-pool-");
        executor.initialize();
        return executor;
    }
}
