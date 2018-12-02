package interview.challenge.directed.weighted.graph.interactor.dijkstra;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import interview.challenge.directed.weighted.graph.model.Graph;
import interview.challenge.directed.weighted.graph.model.Node;

class DijkstraNodes {

	private Map<String, NameAndDistance> nodeWithDistanceByName = null;
	private SortedSet<NameAndDistance> nodesOrderByDistances = null;

	public DijkstraNodes(Graph graph, String startName) {
		List<Node> nodes = graph.getNodes();
		nodeWithDistanceByName = nodes.stream().collect(Collectors.toMap(Node::getName, NameAndDistance::new));
		nodeWithDistanceByName.remove(startName);
		nodeWithDistanceByName.put(startName, new NameAndDistance(startName, 0));
		nodesOrderByDistances = new TreeSet<NameAndDistance>();
		nodesOrderByDistances.addAll(nodeWithDistanceByName.values());
	}

	public Optional<NameAndDistance> getAndRemoveFirst() {
		if (nodesOrderByDistances.isEmpty()) {
			return Optional.empty();
		}
		NameAndDistance first = nodesOrderByDistances.first();
		if (!first.getHasDistance()) {
			return Optional.empty();
		}
		nodesOrderByDistances.remove(first);
		return Optional.ofNullable(first);
	}

	public void updateNodeDistance(String name, Integer distance) {
		NameAndDistance node = nodeWithDistanceByName.get(name);
		if (node.getDistance() > distance || node.getDistance() == 0) {
			nodesOrderByDistances.remove(node);
			nodeWithDistanceByName.remove(name);
			NameAndDistance node2 = new NameAndDistance(name, distance);
			nodesOrderByDistances.add(node2);
			nodeWithDistanceByName.put(name, node2);
		}
	}

	public boolean isEmpty() {
		return nodesOrderByDistances.isEmpty();
	}

	public Optional<NameAndDistance> getByName(String name) {
		return Optional.ofNullable(nodeWithDistanceByName.get(name));
	}

}
