package hu.uni.miskolc.ai.searchalgorithms;

import com.fasterxml.jackson.databind.ObjectMapper;

import hu.uni.miskolc.ai.searchalgorithms.algorithm.GraphSearch;
import hu.uni.miskolc.ai.searchalgorithms.model.Graph;
import hu.uni.miskolc.ai.searchalgorithms.model.Node;
import hu.uni.miskolc.ai.searchalgorithms.model.Path;
import hu.uni.miskolc.ai.searchalgorithms.model.PathComparator;
import hu.uni.miskolc.ai.searchalgorithms.util.AlgorithmRegistry;
import hu.uni.miskolc.ai.searchalgorithms.util.GraphTestData;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class GraphSearchTest {

	static Stream<GraphTestData> provideGraphs() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		File file = new File("src/test/resources/graphs.json");
		GraphTestData[] graphTestDataArray = objectMapper.readValue(file, GraphTestData[].class);
		return Stream.of(graphTestDataArray);
	}

	@TestFactory
	List<DynamicTest> testGraphSearchAlgorithms() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		File file = new File("src/test/resources/graphs.json");
		GraphTestData[] graphTestDataArray = objectMapper.readValue(file, GraphTestData[].class);
		PathComparator pathComparator = new PathComparator();

		List<DynamicTest> dynamicTests = new ArrayList<>();
		for (GraphTestData graphData : graphTestDataArray) {
			// Initialize graph
			Graph graph = new Graph();
			graphData.nodes.forEach(node -> graph.addNode(new Node(node)));
			graphData.edges.forEach(edge -> graph.addEdge(edge.source, edge.target, edge.weight));

			if (graphData.heuristic != null) {
				graph.setHeuristics(graphData.heuristic);
			}

			for (Map.Entry<String, String> entry : graphData.expectedPaths.entrySet()) {
				String algorithmName = entry.getKey();
				Path expectedPath = new Path(entry.getValue().chars().mapToObj((c) -> String.valueOf((char) c))
						.map((e) -> graph.getNode(e)).toList());

				dynamicTests.add(dynamicTest("Testing " + algorithmName + " on " + graphData.graphId, () -> {
					GraphSearch algorithm = AlgorithmRegistry.getAlgorithm(algorithmName);
					if (algorithm == null) {
						throw new IllegalArgumentException("Algorithm not found: " + algorithmName);
					}

					Path resultPath = algorithm.search(graph, graphData.start, graphData.goal);

					assertEquals(0, pathComparator.compare(expectedPath, resultPath),
							"Algorithm failed: " + algorithmName + " on graph: " + graphData.graphId + " result: "
									+ resultPath + " expected: " + expectedPath);
				}));
			}
		}

		return dynamicTests;
	}
}
