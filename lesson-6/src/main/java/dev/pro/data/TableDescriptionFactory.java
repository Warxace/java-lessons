package dev.pro.data;

import javax.naming.OperationNotSupportedException;
import java.lang.reflect.Field;
import java.util.HashMap;

public class TableDescriptionFactory {
    public static TableDescription GetTableDescription(Class<?> entityClass) throws OperationNotSupportedException {
        TableDescription tableDescription = new TableDescription(getTableName(entityClass));
        for (var field :
                entityClass.getDeclaredFields()) {
            ColumnDescription columnDescription = getColumnDescription(field);
            tableDescription.addColumn(columnDescription);
        }
        return tableDescription;
    }

    private static ColumnDescription getColumnDescription(Field field) throws OperationNotSupportedException {
        AppColumn columnAnnotation = field.getAnnotation(AppColumn.class);
        String columnName = getColumnName(field, columnAnnotation);
        String type = getColumnType(field, columnAnnotation);

        ColumnDescription columnDescription = new ColumnDescription(columnName, type);
        if (columnAnnotation.isPrimaryKey()) {
            columnDescription.setPrimaryKey(true);
        }
        return columnDescription;
    }

    private static String getColumnType(Field field, AppColumn columnAnnotation) throws OperationNotSupportedException {
        String type = columnAnnotation.dbType();
        if (!type.isBlank())
            return type;
        var javaType = field.getType();
        HashMap<Class, String> mapping = getTypeMapping();
        if (!mapping.containsKey(javaType)) {
            throw new OperationNotSupportedException(String.format("Type {%s} not supported", javaType));
        }
        return mapping.get(javaType);
    }

    private static HashMap<Class, String> getTypeMapping() {
        HashMap<Class, String> mapping = new HashMap<>();
        mapping.put(String.class, "String");
        mapping.put(int.class, "Integer");
        return mapping;
    }

    private static String getColumnName(Field field, AppColumn columnAnnotation) {
        var columnName = columnAnnotation.name();
        if (columnName.isBlank()) {
            columnName = field.getName();
        }
        return columnName;
    }

    private static String getTableName(Class<?> entityClass) {
        var tableName = entityClass.getAnnotation(AppTable.class).name();
        if (tableName.isBlank()) {
            var nameParts = entityClass.getName().split("\\.");
            tableName = nameParts[nameParts.length - 1];
        }
        return tableName;
    }
}

