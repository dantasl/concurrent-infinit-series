package com.concurrent.workStealingThreads;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Task implements Callable<Double> {
    private final int term;

    public Task(int term) {
        this.term = term;
    }


    @Override
    public Double call() throws Exception {
        return this.infinitySeriesTermCalculator(this.term);
    }

    private double infinitySeriesTermCalculator(int term){
        return 1.0 / factorial(term);
    }

    private int factorial(int n) {
        int res = 1;
        for (int i = 2; i < n; i++) {
            res *= i;
        }

        return res;
    }
}
