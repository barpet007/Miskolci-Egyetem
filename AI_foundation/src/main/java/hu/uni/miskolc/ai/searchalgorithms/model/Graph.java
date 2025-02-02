package hu.uni.miskolc.ai.searchalgorithms.model;

import java.util.HashMap;
import java.util.Map;

public class Graph {
	private Map<String, Node> nodes;

	public Graph(Map<String, Node> nodes) {
		super();
		this.nodes = nodes;
	}
	
	public Graph() {
		super();
		this.nodes = new HashMap<String, Node>();
	}
	
	public Node getNode(String name) {
		return this.nodes.get(name);
	}
	
	public void addNode(Node node) {
		this.nodes.put(node.getName(), node);
	}
	
	public void addEdge(Node a, Node b, int length) {
		this.nodes.get(a.getName()).addNeighbor(b, length);
		this.nodes.get(b.getName()).addNeighbor(a, length);
	}
	
	public void addEdge(String a, String b, int length) {
		this.nodes.get(a).addNeighbor(nodes.get(b), length);
		this.nodes.get(b).addNeighbor(nodes.get(a), length);
	}
	
	public void addEdge(Node a, Node b) {
		this.addEdge(a, b, 0);
	}
	
	public void addEdge(String a, String b) {
		this.addEdge(a, b, 0);
	}
	
	public void setHeuristics(Map<String, Integer> heuristic) {
		for(var entry : heuristic.entrySet()) {
			nodes.get(entry.getKey()).setHeuristic(entry.getValue());
		}
	}
}
