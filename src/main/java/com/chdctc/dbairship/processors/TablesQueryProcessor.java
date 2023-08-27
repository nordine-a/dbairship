package com.chdctc.dbairship.processors;

import com.chdctc.dbairship.enums.SupportedDatabases;
import com.chdctc.dbairship.model.Column;
import com.chdctc.dbairship.model.Table;
import com.chdctc.dbairship.service.ColumnsMapping;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class TablesQueryProcessor implements ItemProcessor<Table,String> {

    private final ColumnsMapping columnsMapping;
    private final Logger logger= Logger.getLogger("TablesQueryProcessor");

    public TablesQueryProcessor(SupportedDatabases supportedDatabases, ColumnsMapping columnsMapping) {
        this.columnsMapping = columnsMapping;
    }


    @Override
    public String process(@NonNull Table table) throws Exception {
        return generateQuery(table);
    }
    private String generateQuery(Table table){
        StringBuilder stringBuilder= new StringBuilder("CREATE TABLE " + table.getName() + " ( ");

        for (Column column: table.getColumns()){
            //stringBuilder.append(column.getName() +" "+ columnsMapping.getType(column.getDataType(), supportedDatabases));
            stringBuilder.append(column.getName() +" "+ column.getDataTypeName());

            if(column.getColumnLength()!= Integer.MAX_VALUE && columnsMapping.acceptsLength(column.getDataType()))
                stringBuilder.append("("+column.getColumnLength()+ ")");
            stringBuilder.append(", ");
        }

        String temp= stringBuilder.toString();
        String result= temp.substring(0, temp.length()-2)+ ")";
        logger.info("query: "+ result);
        return result;
    }
}
