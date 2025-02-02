
package hu.uni.miskolc.ai.searchalgorithms.algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

import hu.uni.miskolc.ai.searchalgorithms.model.Graph;
import hu.uni.miskolc.ai.searchalgorithms.model.Node;
import hu.uni.miskolc.ai.searchalgorithms.model.Path;

public class DepthFirstSearch extends GraphSearch {

	public DepthFirstSearch() {
	}
	
	public DepthFirstSearch(boolean useExtendedList) {
		super(useExtendedList);
	}

	@Override
	public Path search(Graph graph, Node start, Node goal) {
		Comparator<Path> comparator = new PathLexographicComparator();
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
