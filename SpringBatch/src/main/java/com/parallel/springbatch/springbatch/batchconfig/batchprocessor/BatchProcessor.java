package com.parallel.springbatch.springbatch.batchconfig.batchprocessor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;


public class BatchProcessor implements ItemProcessor<String, String> {


    @Override
    public String process(String item) throws Exception {
        return item.toUpperCase();
    }
}
