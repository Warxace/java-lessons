package dev.pro.collections2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollectionsProcessorTest {
    CollectionsProcessor<Integer> processorUnderTest;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        processorUnderTest = new CollectionsProcessor<Integer>();
    }

    @org.junit.jupiter.api.Test
    void swapShouldChangeElements() {
        Integer[] inputArray = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        processorUnderTest.Swap(inputArray, 2, 5);

        assertEquals(6, inputArray[2]);
        assertEquals(3, inputArray[5]);
    }

    @Test
    void toArrayListShouldCreateArrayList() {
        Integer[] inputArray = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        var arrayList = processorUnderTest.ToArrayList(inputArray);
        for (int i = 0; i < arrayList.size(); i++) {
            assertEquals(inputArray[i], arrayList.get(i));
        }
    }

    @Test
    void countDuplicateShouldReturnKeyCountPairs() {
        String[] inputArray = {"A", "B", "B", "C", "C", "C", "D", "D", "D", "D"};
        var processor = new CollectionsProcessor<String>();
        var counts = processor.countDuplicates(inputArray);

        assertEquals(1, counts.get("A"));
        assertEquals(2, counts.get("B"));
        assertEquals(3, counts.get("C"));
        assertEquals(4, counts.get("D"));
    }
}