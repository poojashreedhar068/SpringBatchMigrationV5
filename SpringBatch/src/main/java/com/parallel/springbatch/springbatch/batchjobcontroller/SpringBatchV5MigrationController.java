package com.parallel.springbatch.springbatch.batchjobcontroller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobInstanceAlreadyExistsException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.JobExecutionEvent;
import org.springframework.boot.autoconfigure.batch.JobExecutionExitCodeGenerator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@RestController
@Slf4j
@RequestMapping("/springbatchmigration")
public class SpringBatchV5MigrationController {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    @Autowired
    private JobOperator jobOperator;


    @GetMapping("/trigger")
    public ResponseEntity<Object> triggerJob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException, JobInstanceAlreadyExistsException, NoSuchJobException {
        log.info("Spring Batch Job Trigger(-) Started");
        JobParameters jobParameters = new JobParametersBuilder().addString("inputString","testing Spring migration").toJobParameters();
        jobLauncher.run(job, jobParameters);
        log.info("Spring Batch Job Trigger(-) Finished");
        return ResponseEntity.accepted().build();

    }


}
