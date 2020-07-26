package com.concurrent.cachedThreads;

import java.util.concurrent.Callable;

public class Task implements Callable<Double> {
    private final int term;

    public Task(int term) {
        this.term = term;
    }

    @Override
    public Double call() throws Exception {
        return this.infinitSeriesTermCalculator(this.term);
    }

    private double infinitSeriesTermCalculator(int term) {
        return 1.0 / factorial(term);
    }

    private int factorial(int n) {
        System.out.println("Thread " + Thread.currentThread().getId() + " is calculating factorial of " + n);

        int res = 1;
        for (int i = 2; i <= n; i++) {
            res *= i;
        }

        return res;
    }
}
