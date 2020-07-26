/*
 * @author Lucas Dantas
 * @author Italo Epifanio
 */
package com.concurrent;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Class Helper.
 * This class provides two basic methods that are called throughout the project.
 */
public class Helper {
	
	/**
	 * Handles with the user input.
	 * Validates the argument provided by the user and returns the length of the 
	 * series to be calculated.
	 *
	 * @param args 		User provided argument.
	 * @return tasks	The series length (or how many tasks the system will create).
	 */
	public static int getSeriesLength(String[] args) {
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
	
	/**
	 * Given a list of terms values, calculates and returns their sum.
	 *
	 * @param terms 	The list of terms.
	 * @return sum		The sum of terms.
	 */
	public static Double calculateSeriesSum(List<Future<Double>> terms) {
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
}
