package interview.challenge.directed.weighted.graph.adapter;

import interview.challenge.directed.weighted.graph.model.Graph;

public interface QueryAdapter {
	public boolean isMatch(String line);
	public String execute(Graph graph, String line);
}
