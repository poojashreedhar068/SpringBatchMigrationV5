package com.parallel.springbatch.springbatch.batchconfig.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.annotation.AfterWrite;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@EnableBatchProcessing
public class BatchListener implements StepExecutionListener {

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("After Step Execution");
        return ExitStatus.COMPLETED;
    }

}
