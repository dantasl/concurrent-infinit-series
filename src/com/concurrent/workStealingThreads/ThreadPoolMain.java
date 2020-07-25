package com.concurrent.workStealingThreads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolMain {
    private static final int N = 10;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newWorkStealingPool();
        List<Future<Double>> results =
                new ArrayList<Future<Double>>();

        for (int i = 0; i < 2; i++) {
            Callable<Double> task = new Task(i);
            Future<Double> result = executor.submit(task);
            results.add(result);
        }

        int i = 0;
        try {
            for (Future<Double> result : results) {
                System.out.print("Term of " + i++ + ": ");
                System.out.print(result.get() + "\n");
                i++;
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
