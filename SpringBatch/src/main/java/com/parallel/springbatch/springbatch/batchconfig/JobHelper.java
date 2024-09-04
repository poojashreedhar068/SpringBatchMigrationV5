package com.parallel.springbatch.springbatch.batchconfig;

import org.springframework.batch.core.job.builder.JobBuilderHelper;
import org.springframework.batch.core.repository.JobRepository;

public class JobHelper extends JobBuilderHelper {

    public JobHelper(String name, JobRepository jobRepository) {
        super(name, jobRepository);
    }
}
