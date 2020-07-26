package com.concurrent.workStealingThreads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import com.concurrent.Helper;
import com.concurrent.Task;

public class ThreadPoolMain {
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
