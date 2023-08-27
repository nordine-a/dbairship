package com.chdctc.dbairship.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Column {

    private String name;
    private Integer dataType;
    private String dataTypeName;
    private Integer columnLength;

}
