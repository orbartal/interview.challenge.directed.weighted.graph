package interview.challenge.directed.weighted.graph.adapter;

import java.util.Optional;

import interview.challenge.directed.weighted.graph.model.Graph;

//This service is the only one with a state.
public interface GraphStore {
	public long save(Graph graph);

	public Optional<Graph> get(long id);
}
