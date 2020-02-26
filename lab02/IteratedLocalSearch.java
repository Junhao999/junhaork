package com.aim.lab02;
import com.aim.lab01.*;
import java.util.Random;

import com.aimframework.domains.chesc2014_SAT.SAT;
import com.aimframework.satheuristics.SATHeuristic;
import com.aimframework.searchmethods.SinglePointSearchMethod;
import com.aim.lab00.*;
	
public class IteratedLocalSearch extends SinglePointSearchMethod {

	// local search / intensification heuristic
	private SATHeuristic oLocalSearchHeuristic;
	
	// mutation / perturbation heuristic
	private SATHeuristic oMutationHeuristic;
	
	// iom parameter setting
	private int iIntensityOfMutation;
	
	// dos parameter setting
	private int iDepthOfSearch;
	
	/**
	 * 
	 * @param oProblem The problem to be solved.
	 * @param oRandom The random number generator, use this one, not your own!
	 * @param oMutationHeuristic The mutation heuristic.
	 * @param oLocalSearchHeuristic The local search heuristic.
	 * @param iIntensityOfMutation The parameter setting for intensity of mutation.
	 * @param iDepthOfSearch The parameter setting for depth of search.
	 */
	public IteratedLocalSearch(SAT oProblem, Random oRandom, SATHeuristic oMutationHeuristic, 
			SATHeuristic oLocalSearchHeuristic, int iIntensityOfMutation, int iDepthOfSearch) {
		
		super(oProblem, oRandom);
		
		this.oMutationHeuristic = oMutationHeuristic;
		this.oLocalSearchHeuristic = oLocalSearchHeuristic;
		this.iIntensityOfMutation = iIntensityOfMutation;
		this.iDepthOfSearch = iDepthOfSearch;
	}

	/**
	 * 
	 * Main loop for ILS. The experiment framework will continually call this loop until
	 * the allocated time has expired.
	 * 
	 * -- ITERATED LOCAL SEARCH PSEUDO CODE --
	 * 
	 * s <- currentSolution
	 * s' <- s
	 * 
	 * // apply mutation heuristic "iIntensityOfMutation" times
	 * FOR 0 -> intensityOfMutation - 1 DO
	 *     s' <- mutation(s')
	 * END_FOR
	 * 
	 * // apply local search heuristic "iDepthOfSearch" times
	 * FOR 0 -> depthOfSearch - 1 DO
	 *     s' <- localSearch(s')
	 * END_FOR
	 * 
	 * IF f(s') ( < | <= ) f(s) THEN
	 *     accept();
	 * ELIF
	 *     reject();
	 * FI
	 */
	protected void runMainLoop() {

		// TODO implement a single application of Iterated Local Search
		
		for(int i = 0;i < iIntensityOfMutation ;i++) {
			oMutationHeuristic.applyHeuristic(problem);
		
			if(i == iIntensityOfMutation-1) {
				break;
			}
		}
		
		for(int j =0;j < iDepthOfSearch ;j++) {
			oLocalSearchHeuristic.applyHeuristic(problem);
			if(j==iDepthOfSearch-1) {
				break;
			}
		}
	    
		double current = problem.getObjectiveFunctionValue(CURRENT_SOLUTION_INDEX);
		
		double candidate = problem.getObjectiveFunctionValue(CURRENT_SOLUTION_INDEX);
		
		if(current <= candidate ) {
			
			problem.copySolution(CURRENT_SOLUTION_INDEX, BACKUP_SOLUTION_INDEX);
			
		}else {
			
			problem.copySolution(BACKUP_SOLUTION_INDEX, CURRENT_SOLUTION_INDEX);
		}
		
	
	}
	
	public String toString() {
		return "Iterated Local Search";
	}
	
	
}
