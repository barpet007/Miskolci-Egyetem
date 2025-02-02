package hu.uni.miskolc.ai.searchalgorithms.algorithm;

import hu.uni.miskolc.ai.searchalgorithms.model.Graph;
import hu.uni.miskolc.ai.searchalgorithms.model.Node;
import hu.uni.miskolc.ai.searchalgorithms.model.Path;

import java.util.*;

public class BeamSearch extends GraphSearch {

	private static final int BEAM_WIDTH = 2;

	public BeamSearch() {
	}

	public BeamSearch(boolean useExtendedList) {
		super(useExtendedList);
	}

	@Override
	public Path search(Graph graph, Node start, Node goal) {
		Comparator<Path> comparator = new PathHeuristicComparator();
		PriorityQueue<Path> queue = new PriorityQueue<>(comparator);
		queue.add(new Path(start));
		while (queue.size() > 0) {
			List<Path> currentPaths = new ArrayList<>();
			for (int i = 0; i <= Math.min(BEAM_WIDTH, queue.size()); i++) {
				currentPaths.add(queue.poll());
			}

			List<Path> expandedPaths = new ArrayList<>();
			for (Path currentPath : currentPaths) {
				Node lastElement = currentPath.getLast();
				if (lastElement.compareTo(goal) == 0) {
					return currentPath;
				}

				if (inExtendedList(lastElement)) {
					continue;
				}

				this.extendedList.add(lastElement.getName());

				for (Node neighbor : lastElement.getNeighbors()) {
					if (currentPath.getNodes().stream().anyMatch(n -> n.equals(neighbor))) {
						continue;
					}
					Path newPath = new Path(new ArrayList<>(currentPath.getNodes()));
					newPath.addNode(neighbor);
					expandedPaths.add(newPath);
				}
			}
			expandedPaths.sort(comparator);

			for (int i = 0; i < Math.min(BEAM_WIDTH, expandedPaths.size()); i++) {
				queue.add(expandedPaths.get(i));
			}
		}

		return null;
	}
}