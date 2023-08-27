package com.chdctc.dbairship.readers;

import com.chdctc.dbairship.model.Column;
import com.chdctc.dbairship.model.Table;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@Component
public class TablesReader implements ItemReader<Table> {

    private ResultSet tables;
    private final DataSource dataSource;
    private DatabaseMetaData databaseMetaData;
    private Logger logger= Logger.getLogger("tablesReader");


    public TablesReader(@Qualifier("sourceDatasource") DataSource sourceDatasource){
        dataSource= sourceDatasource;
    }


    public void resultSet() throws SQLException {
        Connection connection= dataSource.getConnection();
        databaseMetaData = connection.getMetaData();
        tables = databaseMetaData.getTables(null, null, null, new String[]{"TABLE"});
        connection.close();
    }


    @Override
    public Table read() throws Exception {

        logger.info("read table start");

        Table table= new Table();
        List<Column> columnsList= new ArrayList<>();


        if(tables ==null){
            resultSet();
        }
        if(tables.next()){

            String tableName= tables.getString("TABLE_NAME");
            table.setName(tableName);
            logger.info("read table name " + tableName+ " ");
        }

        else {
            tables.close();
            tables =null;
            return null;
        }

        Connection connection= dataSource.getConnection();
        ResultSet columns =
                databaseMetaData.getColumns(null, null, table.getName(), null);

        while (columns.next()) {
            Column column= new Column();

            column.setName(columns.getString("COLUMN_NAME"));
            column.setDataType(columns.getInt("DATA_TYPE"));
            column.setDataTypeName(columns.getString("TYPE_NAME"));
            column.setColumnLength(columns.getInt("COLUMN_SIZE"));
            columnsList.add(column);
        }
        table.setColumns(columnsList);
        logger.info("read table " + table + "successfully");

        columns.close();
        connection.close();

        return table;
    }
}
