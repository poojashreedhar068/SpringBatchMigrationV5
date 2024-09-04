package com.parallel.springbatch.springbatch.batchconfig.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Value;

public class BatchReader {

    public String read(@Value("#{jobParameters['valueString']}") final String valueString) throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return "testing itemreader";
    }
}
