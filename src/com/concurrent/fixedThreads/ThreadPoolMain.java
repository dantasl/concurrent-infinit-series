package com.concurrent.fixedThreads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import com.concurrent.Helper;
import com.concurrent.Task;

public class ThreadPoolMain {
	private static final int NUM_THREADS = 4;
	
	public static void main(String[] args) {
		int seriesLength = Helper.getSeriesLength(args);
		
		ThreadPoolExecutor executor =
				(ThreadPoolExecutor) Executors.newFixedThreadPool(NUM_THREADS);
		
		List<Future<Double>> terms = new ArrayList<Future<Double>>();

		for (int i = 0; i < seriesLength; i++) {
			Callable<Double> factorialTask = new Task(i);
			Future<Double> term = executor.submit(factorialTask);
			terms.add(term);
		}
		
		double result = Helper.calculateSeriesSum(terms);
		System.out.println("The series sum result is: " + result);
		
		executor.shutdown();
	}
}
