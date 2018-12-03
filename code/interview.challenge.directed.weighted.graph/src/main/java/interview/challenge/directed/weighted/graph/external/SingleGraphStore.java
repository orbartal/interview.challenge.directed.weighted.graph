package interview.challenge.directed.weighted.graph.external;

import java.util.Optional;

import interview.challenge.directed.weighted.graph.adapter.GraphStore;
import interview.challenge.directed.weighted.graph.model.Graph;

public class SingleGraphStore implements GraphStore {

	public static final long GRAPH_ID = 1L;

	// This is the service state.
	private Graph graph = null;

	public long save(Graph graph) {
		this.graph = graph;
		return GRAPH_ID;
	}

	public Optional<Graph> get(long id) {
		if (GRAPH_ID == id) {
			return Optional.ofNullable(graph);
		}
		return Optional.empty();
	}
}
