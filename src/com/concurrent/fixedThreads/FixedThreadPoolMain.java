/*
 * @author Lucas Dantas
 * @author Ítalo Epifânio
 */
package com.concurrent.fixedThreads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import com.concurrent.Helper;
import com.concurrent.Task;

/**
 * This class instantiates and handles with the implementation of fixed sized threaded pool tasks.
 */
public class FixedThreadPoolMain {
	
	/** The constant NUM_THREADS holds the fixed sizes of threads that are running. */
	private static final int NUM_THREADS = 4;
	
	/**
     * The main method.
     * Retrieves, through helper methods, the user input regarding the series length.
     * Instantiates the Executor services and the factorial tasks. At the end, outputs the
     * final result. 
     *
     * @param args 	The series length.
     */
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
