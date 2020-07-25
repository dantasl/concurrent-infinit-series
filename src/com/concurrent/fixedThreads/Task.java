package com.concurrent.fixedThreads;

import java.util.concurrent.Callable;

public class Task implements Callable<Double> {
	private int term;
	private String id;
	
	public Task(String id, int term) {
		this.id = id;
		this.term = term;
		System.out.println("Created: Task " + id);
	}
	
	@Override
	public Double call() throws Exception {
		return this.infinitSeriesTermCalculator(this.term);
	}
	
	private double infinitSeriesTermCalculator(int term) {
		return 1.0 / factorial(term);
	}
	
	private int factorial(int n) {
		System.out.println("Task " + id + " is calculating factorial of " + n);
		
		int res = 1;
		for (int i = 2; i <= n; i++) {
			res *= i;
		}
		
		return res;		
	}
}
