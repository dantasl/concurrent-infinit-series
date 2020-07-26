/*
 * @author Italo Epifânio
 * @author Lucas Dantas
 */

package com.concurrent.workStealingThreads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import com.concurrent.Helper;
import com.concurrent.Task;

/**
 * This class instantiates and handles with the implementation of work stealing pool tasks.
 */
public class WorkStealingThreadPoolMain {
	
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
    	
        ExecutorService executor = Executors.newWorkStealingPool();
       
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
