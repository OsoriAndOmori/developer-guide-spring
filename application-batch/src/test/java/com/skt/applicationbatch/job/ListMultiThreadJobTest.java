package com.skt.applicationbatch.job;

import com.skt.applicationbatch.JobTestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ListMultiThreadJobTest {
    @Autowired
    private JobTestUtils jobTestUtils;

    @Test
    public void testMultiThreadJob() throws Exception {
        JobParametersBuilder paramBuilder = new JobParametersBuilder();
        paramBuilder.addLong("ts", System.currentTimeMillis());

        Map<String, String> param = new HashMap<>();
        param.forEach(paramBuilder::addString);

        JobExecution jobExecution = jobTestUtils.getJobTester("multiThreadJob")
                .launchJob(paramBuilder.toJobParameters());

        jobTestUtils.loggingJobExecution(jobExecution);
    }
}