package hu.uni.miskolc.ai.searchalgorithms.algorithm;

import hu.uni.miskolc.ai.searchalgorithms.model.Graph;
import hu.uni.miskolc.ai.searchalgorithms.model.Node;
import hu.uni.miskolc.ai.searchalgorithms.model.Path;

import java.util.*;

public class BestFirstSearch extends GraphSearch {

	public BestFirstSearch() {}
	
	public BestFirstSearch(boolean useExtendedList) {
		super(useExtendedList, true);
	}

	@Override
	public Path search(Graph graph, Node start, Node goal) {
		Comparator<Path> comparator = new PathHeuristicAndLengthComparator();
		PriorityQueue<Path> queue = new PriorityQueue<>(comparator);
		queue.add(new Path(start));

		while (queue.size() > 0) {
			Path currentPath = queue.remove();
			Node lastElement = currentPath.getLast();

			if(lastElement.compareTo(goal) == 0) {
				return currentPath;
			}

			if (inExtendedList(lastElement)) {
				continue;
			}

			this.extendedList.add(lastElement.getName());

			List<Path> expandedPaths = new ArrayList<>();
			for(Node neighbor : lastElement.getNeighbors()) {
				if (currentPath.getNodes().stream().anyMatch(n -> n.equals(neighbor))) {
					continue;
				}
				Path newPath = new Path(new ArrayList<Node>(currentPath.getNodes()));
				newPath.addNode(neighbor);
				expandedPaths.add(newPath);
			}

			expandedPaths.sort(comparator);
			expandedPaths.forEach(p -> queue.add(p));
		}

		return null;
	}

}