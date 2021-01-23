package dev.pro.collections;

public class MatrixParser {
    final int expectedRows;
    final int expectedCols;

    public MatrixParser(int rows, int cols) {
        expectedRows = rows;
        expectedCols = cols;
    }


    public Integer[][] Parse(String inputArrayString) {
        var rows = inputArrayString.split("\n");
        if (rows.length != expectedRows) {
            throw new MyArraySizeException(expectedCols, expectedRows, 1, rows.length);
        }
        Integer[][] result = new Integer[expectedRows][expectedCols];
        for (int y = 0; y < rows.length; y++) {
            var cells = rows[y].trim().split(" ");
            if (cells.length != expectedCols) {
                throw new MyArraySizeException(expectedCols, expectedRows, cells.length, expectedRows);
            }
            for (int x = 0; x < cells.length; x++) {
                try {
                    result[y][x] = Integer.parseInt(cells[x]);
                    continue;
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(y, x, cells[x]);
                }
            }
        }
        return result;
    }
}
