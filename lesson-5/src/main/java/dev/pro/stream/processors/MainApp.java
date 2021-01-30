package dev.pro.stream.processors;

import java.util.function.Supplier;

public class MainApp {
    public static void main(String[] args) {
        var numbers = NumberProcessor.GetRandomNumbers(100_000_000, 1000_000_000,0);

        measure(()-> NumberProcessor.searchMaxStream(numbers),"Stream");
        measure(()-> NumberProcessor.searchMaxParallelStream(numbers),"ParallelStream");
        measure(()-> NumberProcessor.searchMaxRecurcive(numbers),"RecursiveTask");
    }


    private static void measure(Supplier<Integer> func, String name){
        System.out.println("Start searching with " + name);
        long start = System.currentTimeMillis();
        var max = func.get();
        long end = System.currentTimeMillis();
        System.out.println(String.format("Search finished with %s in %d", name ,end-start));
        System.out.println(String.format("Maxvalue is %d",max));
    }
}
