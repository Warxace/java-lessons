package dev.pro.multithreading;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainApp {
    static final int SIZE = 10_000_000;
    //static final int HALF = SIZE / 2;


    public static void main(String[] args) {
        Simple();
        int degreeOfParallelism = 32;
        ParallelWithCopy(degreeOfParallelism);
        ParallelWithSharing(degreeOfParallelism);
        ParallelWithSharingAndExecutor(degreeOfParallelism);
    }

    public static void Simple() {
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1.0f);
        long a = System.currentTimeMillis();
        calculateArray(arr, 0, SIZE);
        System.out.println(System.currentTimeMillis() - a);
    }

    private static void calculateArray(float[] arr, int start, int end) {
        for (int i = start; i < end; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5f) * Math.cos(0.2f + i / 5f) * Math.cos(0.4f + i / 2f));
        }
    }

    public static void ParallelWithCopy(int degreeOfParallelism) {
        float[] arr = new float[SIZE];
        int partSize = SIZE / degreeOfParallelism;


        Arrays.fill(arr, 1.0f);
        long start = System.currentTimeMillis();
        float[][] arrays = new float[degreeOfParallelism][];
        for (int i = 0; i < degreeOfParallelism; i++) {
            arrays[i] = new float[partSize];
            System.arraycopy(arr, i * partSize, arrays[i], 0, partSize);
        }

        long split = System.currentTimeMillis();
        System.out.println("Split : " + (split - start));
        Thread[] threads = new Thread[degreeOfParallelism];

        for (int i = 0; i < degreeOfParallelism; i++) {
            var array = arrays[i];
            String processName = "Calc" + i;
            threads[i] = new Thread(() -> {
                calculateArray(array, 0, partSize);
                long calc = System.currentTimeMillis();
                System.out.println(processName + " : " + (calc - start));
            });
            threads[i].start();
        }

        try {
            for (int i = 0; i < degreeOfParallelism; i++) {
                threads[i].join();
                System.arraycopy(arrays[i], 0, arr, i * partSize, partSize);
            }
            long join = System.currentTimeMillis();
            System.out.println("Finished parallel with copy : " + (join - start));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void ParallelWithSharing(int degreeOfParallelism) {
        float[] arr = new float[SIZE];
        int partSize = SIZE / degreeOfParallelism;

        Arrays.fill(arr, 1.0f);
        long start = System.currentTimeMillis();
        Thread[] threads = new Thread[degreeOfParallelism];

        for (int i = 0; i < degreeOfParallelism; i++) {
            String processName = "Calc" + i;
            int startPosition = i * partSize;
            int endPosition = (i + 1) * partSize;
            threads[i] = new Thread(() -> {
                calculateArray(arr, startPosition, endPosition);
                long calc = System.currentTimeMillis();
                System.out.println(processName + " : " + (calc - start));
            });
            threads[i].start();
        }

        try {
            for (int i = 0; i < degreeOfParallelism; i++) {
                threads[i].join();
            }
            long join = System.currentTimeMillis();
            System.out.println("Finished parallel with sharing : " + (join - start));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void ParallelWithSharingAndExecutor(int degreeOfParallelism) {
        float[] arr = new float[SIZE];
        int partSize = SIZE / degreeOfParallelism;

        Arrays.fill(arr, 1.0f);
        long start = System.currentTimeMillis();
        var executor = Executors.newFixedThreadPool(12);
        ArrayList<Future<?>> futures = new ArrayList<>();

        for (int i = 0; i < degreeOfParallelism; i++) {
            String processName = "Calc" + i;
            int startPosition = i * partSize;
            int endPosition = (i + 1) * partSize;
            var future = executor.submit(() -> {
                calculateArray(arr, startPosition, endPosition);
                long calc = System.currentTimeMillis();
                System.out.println(processName + " : " + (calc - start));
            });
            futures.add(future);
        }
        executor.shutdown();
        try {
            for (var f : futures) {

                f.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        long join = System.currentTimeMillis();
        System.out.println("Finished parallel with executor : " + (join - start));
    }
}
