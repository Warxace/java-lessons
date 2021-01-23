package dev.pro.collections;

import java.util.ArrayList;

public class ArrayDedublicator {
    public static ArrayList<String> Distinct(ArrayList<String> input){
        ArrayList<String> result = new ArrayList<>(input.size());

        for (String word:             input) {
            var idx = result.indexOf(word);
            if(idx < 0){
                result.add(word);
            }
        }
        return result;
    }
}
