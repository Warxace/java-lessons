package dev.pro.data;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class StatementComposer {

    public String getCreateTableStatement(TableDescription table) {
        var prefix = String.format("CREATE TABLE %s ", table.name);
        List<String> columns = getColumnsStatements(table.getColumns());

        return prefix
                + columns.stream()
                .collect(Collectors.joining(", ", "( ", ");"));
    }

    public String getDropTableStatement(TableDescription table) {
        return String.format("DROP TABLE IF EXISTS %s", table.name);
    }

    private List<String> getColumnsStatements(List<ColumnDescription> columns) {
        return columns.stream()
                .map(column-> getColumnStatement(column))
                .collect(Collectors.toList());
    }

    private String getColumnStatement(ColumnDescription column) {
        String type = getCommonType(column);
        if (column.isPrimaryKey()) {
            type += " PRIMARY KEY";
        }
        return String.format("%s %s", column.name, type);
    }

    private String getCommonType(ColumnDescription columnType) {
        switch (columnType.type) {
            case "Integer": {
                if (columnType.isPrimaryKey()) {
                    return "SERIAL";
                } else {
                    return "int";
                }
            }
            case "String": {
                return "CHARACTER VARYING";
            }
            default: {
                throw new IllegalArgumentException("Unknown type " + columnType.type);
            }
        }
    }


    public String getInsertQuery(List<Employee> newEmployees, TableDescription tableDescription) {
        var tableName = tableDescription.name;
        var columns = tableDescription.getColumns();
        var columnNames = columns.stream()
                .filter(clmn -> !clmn.isPrimaryKey())
                .map(clmn-> clmn.name)
                .collect(Collectors.joining(", "));

        ArrayList<String>  entityValues = new ArrayList<>();

        for (var employee :
                newEmployees) {
            var valuesString = columns.stream()
                    .filter(clmn -> !clmn.isPrimaryKey())
                    .map(clmn -> getValueFor(clmn, employee))
                    .collect(Collectors.joining(", ", "(", ")"));
            entityValues.add(valuesString);
        }

        return String.format("insert into %s (%s) values", tableName, columnNames) +
                entityValues.stream().collect(Collectors.joining(", "));
    }

    private String getValueFor(ColumnDescription column, Employee employee){
        Object value = getValue(column, employee);
        switch (column.type){
            case "String":
                return "'" + value.toString()+"'";
            case "Integer":
                return value.toString();
            default:
                throw new IllegalArgumentException("Unknown column type: "+ column.type);
        }
    }

    private Object getValue(ColumnDescription column, Employee employee){
        try{
            var fieldName = column.name;
            var field = Employee.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(employee);
        }
        catch (Exception exc){
            exc.printStackTrace();
            return "";
        }
    }
}
