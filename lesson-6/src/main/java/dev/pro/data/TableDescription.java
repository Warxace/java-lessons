package dev.pro.data;

import java.util.ArrayList;
import java.util.List;

public class TableDescription {
    public final String name;
    private final ArrayList<ColumnDescription> columns;

    public TableDescription(String tableName) {
        name = tableName;
        columns = new ArrayList<>();
    }

    public void addColumn(ColumnDescription column) {
        columns.add(column);
    }

    public List<ColumnDescription> getColumns() {
        return List.copyOf(columns);
    }
}
