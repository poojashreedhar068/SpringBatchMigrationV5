package com.parallel.springbatch.springbatch.batchconfig.writer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class BatchWriter implements ItemWriter<String> {

    @Autowired
    JobOperator jobOperator;


    @Override
    public void write(Chunk<? extends String> chunk) throws Exception {
        log.info("Executing Batch Writer");
        List<? extends String> str = chunk.getItems();
        for(String s: str)
            log.info(s);

    }
}
