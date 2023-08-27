package com.chdctc.dbairship.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@ToString
public class Table {
    private String name;
    private List<Column> columns;
}
