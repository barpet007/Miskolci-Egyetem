package hu.uni.miskolc.ai.searchalgorithms.model;

public class Edge {
    private final Node destination;
    private final double weight;

    public Edge(Node destination, double weight) {
        this.destination = destination;
        this.weight = weight;
    }

    public Node getDestination() {
        return destination;
    }

    public double getWeight() {
        return weight;
    }

	@Override
	public String toString() {
		return "[" + destination + " " + weight + "]";
	}
}
