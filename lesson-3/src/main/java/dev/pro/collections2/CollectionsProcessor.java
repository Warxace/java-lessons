package dev.pro.collections2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

public class CollectionsProcessor<T> {
    public void Swap(T[] inputArray, int first, int second){
        T swapElement = inputArray[first];
        inputArray[first] = inputArray[second];
        inputArray[second] = swapElement;
    }

    public ArrayList<T> ToArrayList(T[] inputArray){
        ArrayList<T> result = new ArrayList<T>(Arrays.asList(inputArray));
        return result;
    }

    public HashMap<T,Integer> countDuplicates(T[] array){
        HashMap<T,Integer> result = new HashMap<>();
        for (var item:
             array) {
            if (!result.containsKey(item)) {
                result.put(item, 0);
            }
            var count = result.get(item);
            result.put(item, count + 1);
        }

        return result;
    }
}
