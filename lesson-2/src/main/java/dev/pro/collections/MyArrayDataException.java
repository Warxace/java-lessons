package dev.pro.collections;

public class MyArrayDataException extends RuntimeException {

    public final int row;
    public final int col;
    
    public final String data;

    public MyArrayDataException(int row, int col, String data) {
        super(String.format("Cell {%d, %d} has invalid value: %s", row, col, data));
        this.row = row;
        this.col = col;
        this.data = data;
    }
}

