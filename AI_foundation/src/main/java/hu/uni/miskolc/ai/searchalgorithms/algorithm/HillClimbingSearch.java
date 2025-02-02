package hu.uni.miskolc.ai.searchalgorithms.algorithm;

import hu.uni.miskolc.ai.searchalgorithms.model.Graph;
import hu.uni.miskolc.ai.searchalgorithms.model.Node;
import hu.uni.miskolc.ai.searchalgorithms.model.Path;

import java.util.*;

public class HillClimbingSearch extends GraphSearch {

	public HillClimbingSearch() {
		super(false, true);
	}
	
	public HillClimbingSearch(boolean useExtendedList) {
		super(useExtendedList, true);
	}

	@Override
	public Path search(Graph graph, Node start, Node goal) {
		Comparator<Path> comparator = new PathHeuristicComparator();
		Stack<Path> stack = new Stack<Path>();
		stack.add(new Path(start));

		while (stack.size() > 0) {
			Path currentPath = stack.pop();
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
			// reverse because of stack behavior
			expandedPaths.sort(comparator.reversed());
			expandedPaths.forEach(p -> stack.push(p));

		}

		return null;
	}

}