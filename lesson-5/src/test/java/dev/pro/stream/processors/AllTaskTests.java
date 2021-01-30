package dev.pro.stream.processors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class AllTaskTests {
    private static int[] sourceNumbers;
    private static int expectedMaxValue;

    @BeforeAll
    public static void setup(){
        sourceNumbers = NumberProcessor.GetRandomNumbers(100000000, 100000,0);
        for (var number :
                sourceNumbers) {
            expectedMaxValue = Math.max(expectedMaxValue, number);
        }
    }


    @Test
    //Взять строку, состоящую из 100 слов разделенных пробелом, получить список слов длиннее 5 символов,
    // и склеить их в одну строку с пробелом в качестве разделителя;
    public void searchWordsLongerThan5() {
        var sourceStream = Stream.of("Yes", "No", "LongWord", "VeryLongWord", "Cat", "Dog", "", "A", "BB", "CCC")
                .flatMap(word ->  IntStream.range(0, 10)
                        .mapToObj(r -> String.format("%s%d", word, r)))
                .collect(Collectors.joining(" "));
        String expectedString = "LongWord0 LongWord1 LongWord2 LongWord3 LongWord4 LongWord5 LongWord6 LongWord7 LongWord8 LongWord9 "
                + "VeryLongWord0 VeryLongWord1 VeryLongWord2 VeryLongWord3 VeryLongWord4 VeryLongWord5 VeryLongWord6 VeryLongWord7 VeryLongWord8 VeryLongWord9";

        //act
        var filteredWords = WordProcessor.searchWordLongerThan(sourceStream, 5);

        assertEquals(expectedString, filteredWords);
    }

    @Test
    //Найти список уникальных слов в двумерном массиве размером 5х5;
    public void distinctShouldReturnUniqueWordsFromMatrix(){
        String[][] matrix = new String[5][];
        matrix[0] = new String[]{"A","BB","CCC", "D","E"};
        matrix[1] = new String[]{"A","AA","CC", "CCC","B"};
        matrix[2] = new String[]{"CC","B","D", "DD","A"};
        matrix[3] = new String[]{"BBB","AA","B", "EE","E"};
        matrix[4] = new String[]{"DDD","E","DD", "EEE","BB"};

        List<String> expectedList = List.of("A", "AA","B", "BB", "BBB", "CC", "CCC","D", "DD","DDD", "E", "EE", "EEE");

        //act
        var distinctList = WordProcessor.distinct(matrix);

        assertEquals(expectedList,distinctList);
    }

    @Test
    //Посчитать сумму четных чисел в пределах от 100 до 200 (включительно);
    void countEvenNumbers() {
        List<Integer> sourceValues = IntStream.rangeClosed(100, 200)
                .boxed()
                .collect(Collectors.toList());
        //act
        long count = NumberProcessor.CountEvenNumbers(sourceValues);

        assertEquals(51,count);
    }

    @Test
    //Посчитать суммарную длину строк в одномерном массиве
    void getSummarizedLengthTest() {
        var sourceStrings = List.of("A", "BB", "CCC", "DDDD", "EEEEE");

        //act
        var sumLength = WordProcessor.getSummarizedLength(sourceStrings);

        assertEquals(15, sumLength);
    }

    @Test
    //Из массива слов получить первые три слова в алфавитном порядке;
    void shouldReturnFirst3WordsInAlphabeticOrder(){
        var sourceStrings = List.of("B", "D", "A", "C", "F","Z");

        var words = WordProcessor.getFirstN(sourceStrings, 3);

        var expected = List.of("A","B","C");
        assertEquals(expected,words);
    }

    @Test
    //Реализуйте поиск максимального элемента в целочисленном массиве (int[]).
    // Размер массива: 100.000.000, в каждой ячейке лежит случайное число от 0 до 100000.
    // С помощью RecursiveTask и ForkJoinPool.commonPool()
    void searchMaxTestRecursive(){
        var max = NumberProcessor.searchMaxRecurcive(sourceNumbers);

        assertEquals(expectedMaxValue,max);
    }

    @Test
    //Реализуйте поиск максимального элемента в целочисленном массиве (int[]).
    // Размер массива: 100.000.000, в каждой ячейке лежит случайное число от 0 до 100000.
    //выполните через stream()
    void searchMaxTestStream(){
        var max = NumberProcessor.searchMaxStream(sourceNumbers);

        assertEquals(expectedMaxValue,max);
    }

    @Test
        //Реализуйте поиск максимального элемента в целочисленном массиве (int[]).
        // Размер массива: 100.000.000, в каждой ячейке лежит случайное число от 0 до 100000.
        //выполните через parallelStream()
    void searchMaxTestParallelStream(){
        var max = NumberProcessor.searchMaxParallelStream(sourceNumbers);

        assertEquals(expectedMaxValue,max);
    }


}