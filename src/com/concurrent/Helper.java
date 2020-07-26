package com.concurrent;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Helper {
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
