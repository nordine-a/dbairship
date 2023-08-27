package com.chdctc.dbairship.config;


import com.chdctc.dbairship.enums.SupportedDatabases;
import com.zaxxer.hikari.pool.HikariProxyDatabaseMetaData;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

@Configuration
public class DatabaseMetadataConfig {

    private final DataSource targetDatasource;

    public DatabaseMetadataConfig(@Qualifier("targetDatasource") DataSource targetDatasource) {
        this.targetDatasource = targetDatasource;
    }

    @Bean
    public SupportedDatabases targetDatabaseType() throws SQLException {
        Connection connection= targetDatasource.getConnection();

        DatabaseMetaData databaseMetaData = connection.getMetaData();
        String dbName= databaseMetaData.getDatabaseProductName();
        connection.close();
        switch (dbName){
            case "PostgreSQL": return SupportedDatabases.POSTGRESQL;
            case "MySQL": return SupportedDatabases.MYSQL;
            case "Oracle": return SupportedDatabases.ORACLE;
            //todo create personalized exception
            default: throw new RuntimeException("Database non supportato");
        }

    }

}
