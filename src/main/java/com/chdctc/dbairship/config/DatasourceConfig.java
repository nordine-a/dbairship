package com.chdctc.dbairship.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import javax.sql.DataSource;


@Configuration
public class DatasourceConfig {

    @Bean
    @Primary
    public DataSource batchDatasource(){
        return DataSourceBuilder.create()
                .url(System.getProperty("batchUrl"))
                .password(System.getProperty("batchPassword"))
                .username(System.getProperty("batchUsername"))
                .driverClassName(System.getProperty("batchDriverClassName"))
                .build();

    }

    @Bean
    public DataSource sourceDatasource(){
         return DataSourceBuilder
                 .create()
                 .url(System.getProperty("sourceUrl"))
                 .username(System.getProperty("sourceUsername"))
                 .password(System.getProperty("sourcePassword"))
                 .driverClassName(System.getProperty("sourceDriverClassName"))
                 .build();
    }

    @Bean
    public DataSource targetDatasource(){
        return DataSourceBuilder
                .create()
                .url(System.getProperty("targetUrl"))
                .username(System.getProperty("targetUsername"))
                .password(System.getProperty("targetPassword"))
                .driverClassName(System.getProperty("targetDriverClassName"))
                .build();
    }


}
