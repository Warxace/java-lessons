package dev.pro.stream.processors;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WordProcessor {
    public static String searchWordLongerThan(String inputWords, int minimalLength){
        var result = Arrays.stream(inputWords.split(" "))
                .filter(word -> word.length() > minimalLength)
                .collect(Collectors.joining(" "));
        return result;
    }

    public static List<String> distinct(String[][] matrix){
        return  Arrays.stream(matrix)
                .flatMap(row -> Arrays.stream(row))
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public static long getSummarizedLength(List<String> sourceStrings) {
        return sourceStrings.stream()
                .map(s-> s.length())
                .mapToInt(l->l)
                .sum();
    }

    public static List<String> getFirstN(List<String> sourceWords, int count) {
        if(count <= 0)
            throw new IllegalArgumentException(String.format("Count {%d} should be greater than 0", count));

        return sourceWords.stream()
                .sorted()
                .limit(count)
                .collect(Collectors.toList());
    }
}

