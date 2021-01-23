package dev.pro.collections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixParserTest {
    MatrixParser parserUnderTest;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        parserUnderTest = new MatrixParser(4,4);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @Test
    void parseShouldThrowExceptionWhenArraySizeIsWrong() {
        String threeRowsArray = "1 2 3 4 \n 5 6 7 8 \n 9 10 11 12";
        String threeColsArray = "1 2 3 \n 4 5 6 \n 7 8 9 \n 10 11 12";


        MyArraySizeException exception = assertThrows(MyArraySizeException.class, ()-> parserUnderTest.Parse(threeRowsArray));
        assertEquals(3, exception.actualRows);

        exception = assertThrows(MyArraySizeException.class, ()-> parserUnderTest.Parse(threeColsArray));
        assertEquals(3, exception.actualCols);
    }

    @Test
    void parseShouldReturnMatrix() {
        String integersArray = "1 2 3 4 \n 5 6 7 8 \n 9 10 11 12 \n 13 14 15 16";
        var matrix = parserUnderTest.Parse(integersArray);

        int summ = 0;
        for (var row : matrix) {
            for (var cell : row) {
                summ += cell;
            }
        }
        assertEquals(78+40+18, summ);
    }

    @Test
    void parseShouldThrowExceptionWhenNotNumber(){
        String wrongArray = "a 2 3 4 \n 5 6 7 8 \n 9 10 11 12 \n 13 14 15 16";
        var exception = assertThrows(MyArrayDataException.class, ()-> parserUnderTest.Parse(wrongArray));

        assertEquals(0, exception.row);
        assertEquals(0, exception.col);
    }
}