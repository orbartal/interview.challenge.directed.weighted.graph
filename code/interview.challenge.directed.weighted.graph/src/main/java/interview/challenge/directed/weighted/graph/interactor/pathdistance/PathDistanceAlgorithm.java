package interview.challenge.directed.weighted.graph.interactor.pathdistance;

import java.util.Optional;

import interview.challenge.directed.weighted.graph.model.Edge;
import interview.challenge.directed.weighted.graph.model.Graph;

public class PathDistanceAlgorithm {

	public int getRouteDistance(Graph graph, String[] nodes) {
		int sum = 0;
		for (int i = 0; i < nodes.length - 1; i++) {
			Optional<Edge> edge = graph.getEdgeByNodesNames(nodes[i], nodes[i + 1]);
			if (edge.isPresent()) {
				sum = sum + edge.get().getWeight();
			} else {
				throw new RuntimeException("Edge not found");
			}
		}
		return sum;
	}

}
