/*
 * @author Ítalo Epifânio
 * @author Lucas Dantas
 */
package com.concurrent;

import java.util.concurrent.Callable;

/**
 * The Class Task.
 * This class represents the task that will calculate the factorial of a given number.
 */
public class Task implements Callable<Double> {
	
	/** The term (i.e. the number to calculate its factorial). */
	private int term;
	
	/**
	 * Instantiates a new task.
	 *
	 * @param term The term to calculate factorial.
	 */
	public Task(int term) {
		this.term = term;
	}
	
	/**
	 * Call.
	 * Invokes the method responsible for calculating this infinite series term.
	 *
	 * @return The term, calculated.
	 * @throws Exception.
	 */
	@Override
	public Double call() throws Exception {
		return this.infiniteSeriesTermCalculator(this.term);
	}
	
	/**
	 * Calls the factorial of given number and returns it as a series term.
	 *
	 * @param term 			The value to have its factorial calculated.
	 * @return seriesTerm	Returns the value calculated as a series term.	
	 */
	private double infiniteSeriesTermCalculator(int term) {
		return 1.0 / factorial(term);
	}
	
	/**
	 * Calculates the factorial of a given number.
	 *
	 * @param n 			The number to calculate its factorial.
	 * @return factorial	The factorial of given number.
	 */
	private int factorial(int n) {
		System.out.println("Thread " + Thread.currentThread().getId() + " is calculating factorial of " + n);
		
		int res = 1;
		for (int i = 2; i <= n; i++) {
			res *= i;
		}
		
		return res;		
	}
}