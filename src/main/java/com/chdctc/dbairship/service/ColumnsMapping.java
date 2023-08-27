package com.chdctc.dbairship.service;


import com.chdctc.dbairship.enums.SupportedDatabases;
import org.springframework.stereotype.Service;

import java.sql.Types;
import java.util.*;

@Service
public class ColumnsMapping {


    //todo per oracle andrebbe gestito visto l'uso di number()
    //todo trovare formula di conversione lunghezza per i timestamp
    List<Integer> acceptsLength= Arrays.asList(
            -16, -15, -9, -1, 1, 12, 2003, 2004, 2005, 2006, 2011
    );

    private Map<Integer, List<String>> map = new HashMap<Integer, List<String>>() {{
        put(Types.BIT, new ArrayList<String>() {{
            add("BIT");
            add("TINYINT");
            add("NUMBER");
        }});
        put(Types.TINYINT, new ArrayList<String>() {{
            add("SMALLINT");
            add("TINYINT");
            add("NUMBER");
        }});
        put(Types.SMALLINT, new ArrayList<String>() {{
            add("SMALLINT");
            add("SMALLINT");
            add("NUMBER");
        }});
        put(Types.INTEGER, new ArrayList<String>() {{
            add("INTEGER");
            add("INT");
            add("NUMBER");
        }});
        put(Types.BIGINT, new ArrayList<String>() {{
            add("BIGINT");
            add("BIGINT");
            add("NUMBER");
        }});
        put(Types.FLOAT, new ArrayList<String>() {{
            add("REAL");
            add("FLOAT");
            add("NUMBER");
        }});
        put(Types.REAL, new ArrayList<String>() {{
            add("REAL");
            add("REAL");
            add("NUMBER");
        }});
        put(Types.DOUBLE, new ArrayList<String>() {{
            add("DOUBLE PRECISION");
            add("DOUBLE");
            add("NUMBER");
        }});
        put(Types.NUMERIC, new ArrayList<String>() {{
            add("NUMERIC");
            add("NUMERIC");
            add("NUMBER");
        }});
        put(Types.DECIMAL, new ArrayList<String>() {{
            add("NUMERIC");
            add("DECIMAL");
            add("NUMBER");
        }});
        put(Types.CHAR, new ArrayList<String>() {{
            add("CHAR");
            add("CHAR");
            add("CHAR");
        }});
        put(Types.VARCHAR, new ArrayList<String>() {{
            add("VARCHAR");
            add("VARCHAR");
            add("VARCHAR2");
        }});
        put(Types.LONGVARCHAR, new ArrayList<String>() {{
            add("TEXT");
            add("TEXT");
            add("CLOB");
        }});
        put(Types.DATE, new ArrayList<String>() {{
            add("DATE");
            add("DATE");
            add("DATE");
        }});
        put(Types.TIME, new ArrayList<String>() {{
            add("TIME");
            add("TIME");
            add("TIMESTAMP");
        }});
        put(Types.TIMESTAMP, new ArrayList<String>() {{
            add("TIMESTAMP");
            add("DATETIME");
            add("TIMESTAMP");
        }});
        put(Types.BINARY, new ArrayList<String>() {{
            add("BYTEA");
            add("BINARY");
            add("RAW");
        }});
        put(Types.VARBINARY, new ArrayList<String>() {{
            add("BYTEA");
            add("VARBINARY");
            add("RAW");
        }});
        put(Types.LONGVARBINARY, new ArrayList<String>() {{
            add("BYTEA");
            add("LONGBLOB");
            add("BLOB");
        }});
        put(Types.NULL, new ArrayList<String>() {{
            add("NULL");
            add("NULL");
            add("NULL");
        }});
        put(Types.BLOB, new ArrayList<String>() {{
            add("BYTEA");
            add("BLOB");
            add("BLOB");
        }});
        put(Types.CLOB, new ArrayList<String>() {{
            add("TEXT");
            add("TEXT");
            add("CLOB");
        }});
        put(Types.BOOLEAN, new ArrayList<String>() {{
            add("BOOLEAN");
            add("BOOLEAN");
            add("NUMBER");
        }});
        put(Types.NCHAR, new ArrayList<String>() {{
            add("CHAR");
            add("NCHAR");
            add("NCHAR");
        }});
        put(Types.NVARCHAR, new ArrayList<String>() {{
            add("VARCHAR");
            add("NVARCHAR");
            add("NVARCHAR2");
        }});
        put(Types.LONGNVARCHAR, new ArrayList<String>() {{
            add("TEXT");
            add("LONGTEXT");
            add("NCLOB");
        }});
        put(Types.NCLOB, new ArrayList<String>() {{
            add("TEXT");
            add("LONGTEXT");
            add("NCLOB");
        }});
        put(Types.ROWID, new ArrayList<String>() {{
            add("OID");
            add("BINARY");
            add("ROWID");
        }});
        put(Types.SQLXML, new ArrayList<String>() {{
            add("XML");
            add("XML");
            add("XMLTYPE");
        }});
        put(Types.STRUCT, new ArrayList<String>() {{
            add("USER-DEFINED");
            add("JSON");
            add("USER-DEFINED");
        }});
    }};


    public String getType(Integer type, SupportedDatabases supportedDatabases){
        return map.get(type).get(supportedDatabases.getValue());
    }

    public boolean acceptsLength(Integer type){
        int elem= Collections.binarySearch(acceptsLength, type);
        return elem>=0;
    }
}
