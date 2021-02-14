package dev.pro.data;

public class ColumnDescription {
    public final String name;
    public final String type;
    private boolean isPrimaryKey = false;

    public ColumnDescription(String columnName, String type) {
        name = columnName;
        this.type = type;
    }


    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public void setPrimaryKey(boolean isPrimaryKey) {
        this.isPrimaryKey = isPrimaryKey;
    }
}
