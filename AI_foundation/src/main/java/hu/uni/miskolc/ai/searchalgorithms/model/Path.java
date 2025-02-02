package hu.uni.miskolc.ai.searchalgorithms.model;

import java.util.ArrayList;
import java.util.List;

public class Path {
	private List<Node> nodes;
	private int length = 0;

	public Path(List<Node> nodes) {
		this(nodes, 0);
	}
	
	public Path(List<Node> nodes, int length) {
		super();
		this.nodes = nodes;
		this.length = length;
	}

	public Path(Node startNode) {
		super();
		this.nodes = new ArrayList<Node>();
		this.nodes.add(startNode);
	}

	public int getLength() {
		return this.length;
	}

	public void addLength(int length) {
		this.length += length;
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	public void addNode(Node node) {
		this.nodes.add(node);
	}
	
	public void addNode(Node node, int length) {
		this.nodes.add(node);
		this.length += length;
	}

	public Node getLast() {
		return this.nodes.get(this.nodes.size() - 1);
	}

	public int getHeuristic() {
		return this.getLast().getHeuristic();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		this.nodes.forEach(n -> sb.append(n.getName()));
		return "(" + sb.toString() + ")";
	}

}
