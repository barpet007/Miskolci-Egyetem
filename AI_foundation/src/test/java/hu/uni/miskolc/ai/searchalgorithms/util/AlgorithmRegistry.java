package hu.uni.miskolc.ai.searchalgorithms.util;

import java.util.HashMap;
import java.util.Map;

import hu.uni.miskolc.ai.searchalgorithms.algorithm.AStarSearch;
import hu.uni.miskolc.ai.searchalgorithms.algorithm.BeamSearch;
import hu.uni.miskolc.ai.searchalgorithms.algorithm.BestFirstSearch;
import hu.uni.miskolc.ai.searchalgorithms.algorithm.BranchAndBoundSearch;
import hu.uni.miskolc.ai.searchalgorithms.algorithm.BreadthFirstSearch;
import hu.uni.miskolc.ai.searchalgorithms.algorithm.DepthFirstSearch;
import hu.uni.miskolc.ai.searchalgorithms.algorithm.GraphSearch;
import hu.uni.miskolc.ai.searchalgorithms.algorithm.HillClimbingSearch;

public class AlgorithmRegistry {
    private static final Map<String, GraphSearch> algorithms = new HashMap<>();

    static {
        algorithms.put("dfs", new DepthFirstSearch());
        algorithms.put("bfs", new BreadthFirstSearch());
        algorithms.put("hill-climbing", new HillClimbingSearch());
        algorithms.put("best-first", new BestFirstSearch());
        algorithms.put("beam", new BeamSearch());
        algorithms.put("branch-and-bound", new BranchAndBoundSearch());
        algorithms.put("branch-and-bound-heuristic", new BranchAndBoundSearch(false, true));
        algorithms.put("branch-and-bound-extended-set", new BranchAndBoundSearch(true));
        algorithms.put("A*", new AStarSearch());
    }

    public static GraphSearch getAlgorithm(String name) {
        return algorithms.get(name);
    }
}

