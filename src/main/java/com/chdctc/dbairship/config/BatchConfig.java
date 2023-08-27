package com.chdctc.dbairship.config;


import com.chdctc.dbairship.model.Table;
import com.chdctc.dbairship.processors.TablesQueryProcessor;
import com.chdctc.dbairship.readers.TablesReader;
import com.chdctc.dbairship.writers.TablesItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;



@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final TablesReader tablesReader;
    private final TablesItemWriter tablesItemWriter;
    private final TablesQueryProcessor tablesQueryProcessor;

    public BatchConfig(JobBuilderFactory jobBuilderFactory,
                       StepBuilderFactory stepBuilderFactory,
                       TablesReader tablesReader,
                       TablesItemWriter tablesItemWriter,
                       TablesQueryProcessor tablesQueryProcessor) {

        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.tablesReader = tablesReader;
        this.tablesItemWriter = tablesItemWriter;
        this.tablesQueryProcessor = tablesQueryProcessor;
    }




    @Bean
    public Step step(){
        return stepBuilderFactory.
                get("Tables").
                <Table, String> chunk(10)
                .reader(tablesReader)
                .processor(tablesQueryProcessor)
                .writer(tablesItemWriter)
                .build();
    }

    @Bean
    public Job job(){
        return jobBuilderFactory.
                get("TablesJob").
                incrementer(new RunIdIncrementer())
                .start(step())
                .build();
    }





}
