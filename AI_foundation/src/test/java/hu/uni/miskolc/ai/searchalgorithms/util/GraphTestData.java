package hu.uni.miskolc.ai.searchalgorithms.util;

import java.util.List;
import java.util.Map;

public class GraphTestData {
    public String graphId;
    public String description;
    public String start;
    public String goal;
    public List<String> nodes;
    public List<Edge> edges;
    public Map<String, Integer> heuristic;
    public Map<String, String> expectedPaths;

    public static class Edge {
        public String source;
        public String target;
        public int weight;
    }
}

