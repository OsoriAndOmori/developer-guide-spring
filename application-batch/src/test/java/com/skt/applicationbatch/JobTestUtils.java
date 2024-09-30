package com.skt.applicationbatch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
public class JobTestUtils {
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private ConfigurableEnvironment env;

    public JobLauncherTestUtils getJobTester(String jobName) {
        Job job = applicationContext.getBean(jobName, Job.class);
        JobLauncherTestUtils jobLauncherTestUtils = new JobLauncherTestUtils();
        jobLauncherTestUtils.setJob(job);
        jobLauncherTestUtils.setJobRepository(jobRepository);
        jobLauncherTestUtils.setJobLauncher(jobLauncher);
        return jobLauncherTestUtils;
    }

    public void loggingJobExecution(JobExecution jobExecution) {
        log.info("active profiles : {}", Arrays.toString(env.getActiveProfiles()));

        StringBuilder b = new StringBuilder();
        b.append("Steps Information").append('\n');

        int i = 1;

        for (StepExecution step : jobExecution.getStepExecutions()) {
            b.append("Step").append(i++).append(" name=").append(step.getStepName()).append("\n")
                    .append("\tread:").append(step.getReadCount()).append('\n')
                    .append("\tread skip:").append(step.getReadSkipCount()).append('\n')
                    .append("\tproc skip:").append(step.getProcessSkipCount()).append('\n')
                    .append("\tfilter:").append(step.getFilterCount()).append('\n')
                    .append("\twrite:").append(step.getWriteCount()).append('\n')
                    .append("\twrite skip:").append(step.getWriteSkipCount()).append('\n');
        }

        log.info(b.toString());
    }
}
