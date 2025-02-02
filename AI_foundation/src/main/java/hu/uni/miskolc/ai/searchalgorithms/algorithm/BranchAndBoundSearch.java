package hu.uni.miskolc.ai.searchalgorithms.algorithm;

import hu.uni.miskolc.ai.searchalgorithms.model.Edge;
import hu.uni.miskolc.ai.searchalgorithms.model.Graph;
import hu.uni.miskolc.ai.searchalgorithms.model.Node;
import hu.uni.miskolc.ai.searchalgorithms.model.Path;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class BranchAndBoundSearch extends GraphSearch {

	public BranchAndBoundSearch() {
	}

	public BranchAndBoundSearch(boolean useExtendedList) {
		super(useExtendedList);
	}

	public BranchAndBoundSearch(boolean useExtendedList, boolean useHeuristics) {
		super(useExtendedList, useHeuristics);
	}

	@Override
	public Path search(Graph graph, Node start, Node goal) {
		Comparator<Path> comparator = useHeuristics ? new PathHeuristicAndLengthComparator() : new PathLengthComparator();
		PriorityQueue<Path> queue = new PriorityQueue<>(comparator);
		queue.add(new Path(start));
		while (!queue.isEmpty()) {
			Path currentPath = queue.poll();

			Node lastElement = currentPath.getLast();
			if (lastElement.compareTo(goal) == 0) {
				return currentPath;
			}

			if (inExtendedList(lastElement)) {
				continue;
			}

			this.extendedList.add(lastElement.getName());

			List<Path> expandedPaths = new ArrayList<>();
			for (Edge edge : lastElement.getEdges()) {
				Node neighbor = edge.getDestination();
				if (currentPath.getNodes().stream().anyMatch(n -> n.equals(neighbor))) {
					continue;
				}
				Path newPath = new Path(new ArrayList<>(currentPath.getNodes()), currentPath.getLength());
				newPath.addNode(neighbor);
				newPath.addLength((int) edge.getWeight());
				expandedPaths.add(newPath);
			}
			expandedPaths.sort(comparator);

			for (Path path : expandedPaths) {
				queue.add(path);
			}
		}

		return null;
	}
}