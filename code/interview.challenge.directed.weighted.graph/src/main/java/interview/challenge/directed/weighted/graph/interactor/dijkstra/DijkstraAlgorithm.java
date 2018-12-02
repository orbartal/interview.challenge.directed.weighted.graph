package interview.challenge.directed.weighted.graph.interactor.dijkstra;

import java.util.Map;
import java.util.Map.Entry;

import interview.challenge.directed.weighted.graph.model.Edge;
import interview.challenge.directed.weighted.graph.model.Graph;

import java.util.Optional;

public class DijkstraAlgorithm {

	public Integer getLengthOfShortestPath(Graph graph, String start, String end) {
		DijkstraNodes dijkstraNodes = new DijkstraNodes(graph, start);
		while (true) {
			Optional<NameAndDistance> pFirst = dijkstraNodes.getAndRemoveFirst();
			if (!pFirst.isPresent()) {
				break;
			}
			NameAndDistance first = pFirst.get();
			Map<String, Edge> edgesByTargetName = graph.getEdgesBySourceName(first.getName());
			for (Entry<String, Edge> pair : edgesByTargetName.entrySet()) {
				String targetName = pair.getKey();
				Edge edge = pair.getValue();
				int newDistance = first.getDistance() + edge.getWeight();
				dijkstraNodes.updateNodeDistance(targetName, newDistance);
			}
		}
		Optional<NameAndDistance> pResult = dijkstraNodes.getByName(end);
		if (!pResult.isPresent() || !pResult.get().getHasDistance()) {
			throw new RuntimeException("No path was found");
		}
		return pResult.get().getDistance();
	}
}
