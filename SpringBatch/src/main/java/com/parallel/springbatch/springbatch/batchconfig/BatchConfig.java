package com.parallel.springbatch.springbatch.batchconfig;

import com.parallel.springbatch.springbatch.batchconfig.batchprocessor.BatchProcessor;
import com.parallel.springbatch.springbatch.batchconfig.listener.BatchListener;
import com.parallel.springbatch.springbatch.batchconfig.reader.BatchReader;
import com.parallel.springbatch.springbatch.batchconfig.writer.BatchWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.FlowJobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.io.IOException;

@Slf4j
@Configuration
public class BatchConfig {

    @Bean
    public Job buildJob(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) throws Exception {
        /*return new JobBuilder("SpringBatch5-Migration-JobExecution", jobRepository)
                .start(buildStep(jobRepository, transactionManager))
                .build();*/

        return new FlowJobBuilder(new JobHelper("SpringBatch5-Migration-JobExecution",jobRepository))
                .start(buildStep(jobRepository, transactionManager))
                .end().build();
    }

    @Bean
    public Step buildStep(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) throws Exception {
        return new StepBuilder("SpringBatch5-Migration-StepExecution", jobRepository)
                .listener(new BatchListener())
                .<String,String>chunk(1, transactionManager)
                .reader(getReader(null))
                .processor(this::processorbuild)
                .writer(this::writerBuild)
                .build();
    }


    @Bean
    @StepScope
    public ItemReader<? extends String> getReader(@Value("#{jobParameters['inputString']}") final String inputString) throws IOException {
        return new ItemReader<String>() {
            @Override
            public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
                return inputString;
            }
        };
    }
    private void writerBuild(Chunk<?> objects) throws Exception {
        new BatchWriter().write((Chunk<? extends String>) objects);
    }

    private String processorbuild(Object o) throws Exception {
        return new BatchProcessor().process(o.toString());
    }



}
