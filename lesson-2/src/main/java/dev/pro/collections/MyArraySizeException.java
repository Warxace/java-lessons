package dev.pro.collections;

public class MyArraySizeException extends RuntimeException {
    public final int expectedCols;
    public final int expectedRows;
    public final int actualCols;
    public final int actualRows;

    public MyArraySizeException(int expectedCols, int expectedRows, int actualCols, int actualRows) {
        super(String.format("Wrong matrix size {%d, %d}, when expected {%d, %d}", actualCols, actualRows, expectedCols, expectedRows));
        this.expectedCols = expectedCols;
        this.expectedRows = expectedRows;
        this.actualCols = actualCols;
        this.actualRows = actualRows;
    }
}
