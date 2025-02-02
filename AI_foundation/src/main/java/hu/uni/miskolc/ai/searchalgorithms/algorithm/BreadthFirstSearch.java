package hu.uni.miskolc.ai.searchalgorithms.algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import hu.uni.miskolc.ai.searchalgorithms.model.Graph;
import hu.uni.miskolc.ai.searchalgorithms.model.Node;
import hu.uni.miskolc.ai.searchalgorithms.model.Path;

public class BreadthFirstSearch extends GraphSearch {

	public BreadthFirstSearch() {
		super();
	}
	
	public BreadthFirstSearch(boolean useExtendedList) {
		super(useExtendedList);
	}

	@Override
	public Path search(Graph graph, Node start, Node goal) {
		Comparator<Path> comparator = new PathLexographicComparator();
		Queue<Path> queue = new LinkedList<>();
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
