package dev.pro.collections;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ArrayDedublicatorTest {

    @Test
    public void DistinctShouldReturnUniqueStrings() {
        ArrayList<String> inputStrings = new ArrayList<String>(
                Arrays.asList("A", "B", "C", "D", "D", "E", "E"));
        var distinctArray = ArrayDedublicator.Distinct(inputStrings);

        var result = String.join("", distinctArray);
        assertEquals("ABCDE", result);
    }
}