package interview.challenge.directed.weighted.graph.interactor.dijkstra;

import java.util.regex.Pattern;

import interview.challenge.directed.weighted.graph.adapter.QueryAdapter;
import interview.challenge.directed.weighted.graph.model.Graph;

public class QueryAdapterDijkstra implements QueryAdapter {

	static private final String lineRegex = "the length of the shortest route \\(in terms of distance to travel\\) from [a-z] to [a-z]";
	private final DijkstraAlgorithm algorithm;

	public QueryAdapterDijkstra() {
		this(new DijkstraAlgorithm());
	}
	
	public QueryAdapterDijkstra(DijkstraAlgorithm algorithm) {
		this.algorithm = algorithm;
	}

	@Override
	public boolean isMatch(String line) {
		return Pattern.matches(lineRegex, line);
	}

	@Override
	public String execute(Graph graph, String line) {
		String[] nodes = getParams(line);
		try {
			int result = this.algorithm.getLengthOfShortestPath(graph, nodes[0], nodes[1]);
			return result + "";
		} catch (Exception e) {
			return "NO SUCH ROUTE";
		}
	}

	private String[] getParams(String line) {
		String[] parts = line.split(" ");
		int size = parts.length;
		String[] result = { parts[size - 3], parts[size - 1] };
		return result;
	}

}
