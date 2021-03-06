package com.aim.lab00;

import java.util.Arrays;
import java.util.Random;

import com.aim.ExperimentalSettings;
import com.aimframework.domains.chesc2014_SAT.SAT;


/**
 * Class for running the experiment(s) in Lab 0.
 *
 * @author Warren G. Jackson
 */
public class Lab_00_Runner {
	
	public Result runTest(long seed, int instance, int timeLimit) {
		
		Random random = new Random(seed);
    	SAT sat = new SAT(instance, timeLimit, random);
    	RandomWalk rw = new RandomWalk(sat, random);
    	double num = random.nextDouble();
    	rw.run();
    	
    	return new Result(seed, sat.getBestSolutionValue(), rw.getTimeTaken(),num);
	}
	
	public void printResult(Result result) {
		
		System.out.println(result.seed + "," + result.best + "," + result.timeTaken +","+result.num );

	}
	
	public static void main(String [] args) {
		
		Lab_00_Runner runner = new Lab_00_Runner();
		
		// do not change these values!
		final int instance = 1;
		// note; these are nominal seconds. Your machine may take longer or might be faster!
		final int timeLimit = 10; 
		
		Long[] seeds = { 123456789l };
		
		System.out.println("seed,f_best,time_taken");
		
		if( ExperimentalSettings.ENABLE_PARALLEL_EXECUTION ) {
			
			Arrays.stream(seeds).map( s -> {
				return runner.runTest(s, instance, timeLimit);
			}).forEachOrdered(runner::printResult);
			
		} else {
			
			for(int i = 0; i < seeds.length; i++) {
				
				long seed = seeds[i];
				Result r = runner.runTest(seed, instance, timeLimit);
				runner.printResult(r);
			}
		}
	}
	
	class Result {
		
		protected final long seed;
		
		protected final double best;
		
		protected final double timeTaken;
		
		protected final double num;
		
		public Result(long seed, double best, double timeTaken,double num) {
			
			this.seed = seed;
			this.best = best;
			this.timeTaken = timeTaken;
			this.num=num;
		}
	}
}
