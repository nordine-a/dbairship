package com.chdctc.dbairship.writers;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;


@Component
public class TablesItemWriter implements ItemWriter<String> {
    private DataSource dataSource;

    public TablesItemWriter(@Qualifier("targetDatasource") DataSource sourceDatasource){
        dataSource= sourceDatasource;
    }

    @Override
    public void write(List<? extends String> createTableSQLList) throws Exception {
        Connection connection= dataSource.getConnection();
        Statement statement = connection.createStatement();


        for(String createTableSQL: createTableSQLList){
            statement.executeUpdate(createTableSQL);
        }

        statement.close();
        connection.close();

    }
}
