
package hu.uni.miskolc.ai.searchalgorithms.algorithm;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import hu.uni.miskolc.ai.searchalgorithms.model.Graph;
import hu.uni.miskolc.ai.searchalgorithms.model.Node;
import hu.uni.miskolc.ai.searchalgorithms.model.Path;

public abstract class GraphSearch {
	protected Set<String> extendedList;
	protected boolean useExtendedList;
	protected boolean useHeuristics;

	protected class PathLexographicComparator implements Comparator<Path> {
		@Override
		public int compare(Path a, Path b) {
			return a.toString().compareTo(b.toString());
		}
	}

	protected class PathLengthComparator implements Comparator<Path> {
		private PathLexographicComparator lexographic = new PathLexographicComparator();
		@Override
		public int compare(Path a, Path b) {
			 return Comparator.comparing(Path::getLength)
					.thenComparing(lexographic)
					.compare(a, b);
		}
	}
	
	protected class PathHeuristicComparator implements Comparator<Path> {
		private PathLexographicComparator lexographic = new PathLexographicComparator();
		@Override
		public int compare(Path a, Path b) {
			 return Comparator.comparing(Path::getHeuristic)
					.thenComparing(lexographic)
					.compare(a, b);
		}
	}
	
	protected class PathHeuristicAndLengthComparator implements Comparator<Path> {
		private PathLexographicComparator lexographic = new PathLexographicComparator();
		@Override
		public int compare(Path a, Path b) {
			 return Comparator.comparing((Path p)-> p.getHeuristic() + p.getLength())
					.thenComparing(lexographic)
					.compare(a, b);
		}
	}
	
	protected boolean inExtendedList(Node node) {
		return useExtendedList && extendedList.contains(node.getName());
	}

	public GraphSearch() {
		this(false, false);
	}
	
	public GraphSearch(boolean useExtendedList) {
		this(useExtendedList, false);
	}

	public GraphSearch(boolean useExtendedList, boolean useHeuristics) {
		this.useExtendedList = useExtendedList;
		this.useHeuristics = useHeuristics;
		this.extendedList = new HashSet<>();
	}

	public Path search(Graph graph, String start, String goal) {
		return this.search(graph, graph.getNode(start), graph.getNode(goal));
	}
	
	public abstract Path search(Graph graph, Node start, Node goal);
}
