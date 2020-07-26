package com.concurrent.cachedThreads;

import com.concurrent.workStealingThreads.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolMain {
    private static final int N = 10;

    public static void main(String[] args) {
        // Start stealing work pool
        ExecutorService executor = Executors.newCachedThreadPool();
        // creates a future list to sum the series after the threads execution
        List<Future<Double>> results =
                new ArrayList<Future<Double>>();

        // calls the taks that calculates the 1/n! and add to the future
        for (int i = 0; i < N; i++) {
            Callable<Double> task = new Task(i);
            Future<Double> result = executor.submit(task);
            results.add(result);
        }

        int i = 0;
        double infinitySeriesSum = 0;

        // sum series and print final value
        try {
            for (Future<Double> result : results) {
                System.out.print("Term of " + i++ + ": ");
                System.out.print(result.get() + "\n");

                infinitySeriesSum += result.get();
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
            System.out.println("The series sum value is: " + infinitySeriesSum);
        }
    }
}