package com.aim.lab01;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import com.aimframework.domains.chesc2014_SAT.SAT;
import com.aimframework.helperfunctions.ArrayMethods;
import com.aimframework.satheuristics.SATHeuristic;

public class DavissBitHC extends SATHeuristic {


	public DavissBitHC(Random random) {
		
		super(random);
	}

	
	
	/**
	  * DAVIS's BIT HILL CLIMBING LECTURE SLIDE PSEUDO-CODE
	  *
	  * bestEval = evaluate(currentSolution);
	  * perm = createRandomPermutation();
	  * for(j = 0; j < length[currentSolution]; j++) {
	  * 
	  *     bitFlip(currentSolution, perm[j]); 		//flips j^th bit from permutation of solution producing s' from s
	  *     tmpEval = evaluate(currentSolution);
	  *
	  *     if(tmpEval < bestEval) { 				// if there is improvement (strict improvement)
	  *
	  *         bestEval = tmpEval; 				// accept the best flip
	  *         
	  *     } else { 								// if there is no improvement, reject the current bit flip
	  *
	  *          bitFlip(solution, perm[j]); 		//go back to s from s'
	  *     }
	  * }
	  *
	  * @param problem The problem to be solved.
	  */
	public void applyHeuristic(SAT problem) {
	    //get random list
		 int var = problem.getNumberOfVariables();
		int[] list = new int[var];
		creatList(list,var);
		//IntStream intstream = IntStream.range(0, problem.getNumberOfVariables());
	
		
		//record current solution fitness
	  double bestFitness = problem.getObjectiveFunctionValue(CURRENT_SOLUTION_INDEX);
	  ArrayMethods.shuffle(list,random) ;
	 // System.out.print(Arrays.toString(list));
	  for(int j=0;j<var-1;j++) {
		  
		  problem.bitFlip(list[j]);
		  
		  double currentFitness = problem.getObjectiveFunctionValue(CURRENT_SOLUTION_INDEX);
		  
		  if(currentFitness<bestFitness) {
			  bestFitness = currentFitness;
		  }
		  else {
			  problem.bitFlip(list[j]);
		  }
	  }
	  
	  //bestsolution values指的应该是被更改的variable的数量？
			  
	}

	public String getHeuristicName() {

		return "Davis's Bit HC";
	}

	
	public static int[] creatList(int[] data,int n ) {
        
		for(int i = 0;i<n;i++) {
			data[i] = i+1;
		
	
		}
		return data;
	}
}

