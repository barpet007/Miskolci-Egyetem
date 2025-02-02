package hu.uni.miskolc.ai.searchalgorithms.model;

import java.util.ArrayList;
import java.util.List;

public class Node implements Comparable<Node> {
	private String name;
	private List<Edge> edges;
	private int heuristic = 0;

	public Node(String name, List<Edge> edges, int heuristic) {
		super();
		this.name = name;
		this.edges = edges;
		this.heuristic = heuristic;
	}

	public Node(String name, List<Edge> edges) {
		super();
		this.name = name;
		this.edges = edges;
	}

	public Node(String name) {
		super();
		this.name = name;
		this.edges = new ArrayList<Edge>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Node> getNeighbors() {
		return this.edges.stream().map(Edge::getDestination).toList();
	}

	public void setEdges(List<Edge> neighbors) {
		this.edges = neighbors;
	}

	public void addNeighbor(Node neighbor) {
		this.edges.add(new Edge(neighbor, 0));
	}

	public void addNeighbor(Node neighbor, int distance) {
		this.edges.add(new Edge(neighbor, distance));
	}

	public void addEdge(Edge edge) {
		this.edges.add(edge);
	}
	
	public List<Edge> getEdges() {
		return this.edges;
	}

	public int getHeuristic() {
		return this.heuristic;
	}

	public void setHeuristic(int heuristic) {
		this.heuristic = heuristic;
	}

	@Override
	public int compareTo(Node other) {
		return this.name.compareTo(other.getName());
	}

	@Override
	public String toString() {
		return "Node " + name + "[neighbors=" + edges.stream().map(Edge::getDestination).map(Node::getName).toList() + ", heuristic=" + heuristic + "]";
	}
	
	@Override
	public boolean equals(Object other) {
		if(other == null) {
			return false;
		}
		
		if(!(other instanceof Node)) {
			return false;
		}
		
		return this.name.equals(((Node)other).getName());
	}

}
