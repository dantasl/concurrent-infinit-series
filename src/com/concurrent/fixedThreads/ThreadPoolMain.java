package com.concurrent.fixedThreads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolMain {
	private static final int NUM_THREADS = 4;
	
	public static void main(String[] args) {
		int seriesLength = 30;
		long start = System.currentTimeMillis(); // starts time
		
		ThreadPoolExecutor executor =
				(ThreadPoolExecutor) Executors.newFixedThreadPool(NUM_THREADS);
		
		List<Future<Double>> terms = new ArrayList<Future<Double>>();

		for (int i = 0; i < seriesLength; i++) {
			Callable<Double> factorialTask = new Task(String.valueOf(i), i);
			Future<Double> term = executor.submit(factorialTask);
			terms.add(term);
		}
		
		double result = calculateSeriesSum(terms);
		System.out.println("The series sum result is: " + result);
		
		executor.shutdown();

		long elapsedTime = System.currentTimeMillis() - start;
		System.out.println("Executed in: " + elapsedTime/1000F); // elapsed time in seconds
	}
	
	private static Double calculateSeriesSum(List<Future<Double>> terms) {
		double infinitSeriesSum = 0;
        
        try {
            for (Future<Double> term : terms) {
                infinitSeriesSum += term.get();
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        
        return infinitSeriesSum;
	}
	
	private static int getSeriesLength(String[] args) {
		if (args.length != 1) {
			System.out.println("You must provide the series length as an argument.");
			System.exit(1);
		}
		
		int tasks = 0;
		
		try {
			tasks = Integer.parseInt(args[0]);
		}
		catch (NumberFormatException e) {
			System.out.println("Argument must be an integer.");
			System.exit(1);
		}
		
		return tasks;
	}
}
