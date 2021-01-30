package dev.pro.stream.processors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NumberProcessor {
    public static long CountEvenNumbers(List<Integer> numbers) {
        return numbers.stream()
                .filter(n -> n % 2 == 0)
                .count();
    }

    //For some reason performance is lower than single stream, 45ms in average
    public static int searchMaxRecurcive(int[] sourceNumbers) {
        var recursiveTask = new DemoRecursiveTask(sourceNumbers, 0, sourceNumbers.length - 1);
        Integer result = ForkJoinPool.commonPool().invoke(recursiveTask);
        return result;
    }

    //Middle of performance, 35ms in average
    public static int searchMaxStream(int[] sourceNumbers) {
        return IntStream.of(sourceNumbers)
                .reduce(sourceNumbers[0], (acc, n) -> n > acc ? n : acc);
    }

    //The most efficient method, 25ms in average
    public static int searchMaxParallelStream(int[] sourceNumbers) {
        return IntStream.of(sourceNumbers)
                .parallel()
                .reduce(sourceNumbers[0], (acc, n) -> n > acc ? n : acc);
    }

    public static int[] GetRandomNumbers(int count, int upperBound, int lowerBound) {
        Random random = new Random();
        return random.ints(count, lowerBound, upperBound).toArray();
    }

    static class DemoRecursiveTask extends RecursiveTask<Integer> {
        private int[] data;
        private int start;
        private int end;

        public DemoRecursiveTask(int[] data, int start, int end) {
            this.data = data;
            this.start = start;
            this.end = end;
        }

        protected Integer compute() {
            //System.out.println(String.format("Started computation for [%d,%d]", start,end));
            if ((end - start) > 20000) {
                List<DemoRecursiveTask> subtasks = createSubtasks();
                for (DemoRecursiveTask subtask : subtasks) {
                    subtask.fork();
                }
                int result = 0;
                for (DemoRecursiveTask subtask : subtasks) {
                    int max = subtask.join();
                    result = max > result? max:result;
                }
                //System.out.println(String.format("Max for [%d,%d] is %d", start,end, result));
                return result;
            } else {
                var result = 0;
                for (int i = start; i <= end; i++) {
                    result = data[i] > result? data[i]:result;
                }
                //System.out.println(String.format("Max for [%d,%d] is %d", start,end, result));
                return result;
            }
        }

        private List<DemoRecursiveTask> createSubtasks() {
            var middle = (end + start) / 2;
            return List.of(new DemoRecursiveTask(data, start, middle - 1),
                    new DemoRecursiveTask(data, middle, end));
        }
    }
}
